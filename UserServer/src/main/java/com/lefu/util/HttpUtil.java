package com.lefu.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.lefu.user.exception.HttpException;

public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	private static final String UTF_8 = "UTF-8";
	private static SSLConnectionSocketFactory sslsf = null;
	private static PoolingHttpClientConnectionManager cm = null;
	private static SSLContextBuilder builder = null;
	static {
		try {
			builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s)
						throws CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(), new HostnameVerifier() {
				public boolean verify(String s, SSLSession sslSession) {
					return true;
				}
			});
			Registry<ConnectionSocketFactory> registry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf)
					.build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(200);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static CloseableHttpClient getHttpClient(boolean isSsl) {
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		if (isSsl) {
			httpClientBuilder.setSSLSocketFactory(sslsf);
		}
		CloseableHttpClient httpClient = httpClientBuilder.setConnectionManager(cm)
				.setConnectionManagerShared(true).build();
		return httpClient;
	}

	public static String post(String url) {
		return post(url, null, null);
	}

	public static String get(String url) {
		return get(url, null, null);
	}

	public static String get(String url, Map<String, String> header, Map<String, String> param) {
		return httpRequest(url, HttpGet.METHOD_NAME, header, param);
	}

	public static String post(String url, Map<String, String> header, Map<String, String> param) {
		return httpRequest(url, HttpPost.METHOD_NAME, header, param);
	}

	public static String httpRequest(String url, String methodName, Map<String, String> header,
			Map<String, String> param) {
		logger.info("url:{}, methodName:{}, header:{}, param:{}", url, methodName, header, param);
		CloseableHttpClient httpclient = getHttpClient(url.startsWith(HTTPS));
		try {
			if (param == null) {
				param = new HashMap<String, String>();
			}

			if (HttpPost.METHOD_NAME.equals(methodName)) {
				if (url.indexOf("?") >= 0) {
					String[] split = url.substring(url.indexOf("?") + 1).split("&");
					for (String string : split) {
						String[] split2 = string.split("=");
						if (split2.length == 2) {
							param.put(split2[0], split2[1]);
						}
					}
					url = url.substring(0, url.indexOf("?"));
				}
			}

			RequestBuilder requestBuilder = null;
			if (HttpGet.METHOD_NAME.equals(methodName)) {
				requestBuilder = RequestBuilder.get();
			} else if (HttpPost.METHOD_NAME.equals(methodName)) {
				requestBuilder = RequestBuilder.post();
			} else {
				requestBuilder = RequestBuilder.get();
			}

			if (!CollectionUtils.isEmpty(header)) {
				Set<Entry<String, String>> entrySet = header.entrySet();
				for (Entry<String, String> entry : entrySet) {
					if (StringUtils.hasText(entry.getKey())) {
						requestBuilder.addHeader(entry.getKey(), entry.getValue());
					}
				}
			}

			ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
			if (param != null) {
				Set<Entry<String, String>> entrySet = param.entrySet();
				for (Entry<String, String> entry : entrySet) {
					list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			
			try {
				requestBuilder.setUri(new URI(url));
			} catch (URISyntaxException e) {
				throw new HttpException(e.getMessage(), e);
			}
			
			try {
				requestBuilder.setEntity(new UrlEncodedFormEntity(list, UTF_8));
			} catch (UnsupportedEncodingException e) {
				throw new HttpException(e.getMessage(), e);
			}

			HttpUriRequest request = requestBuilder.build();
			logger.info("Executing request {}", request.getRequestLine());
			Header[] allHeaders = request.getAllHeaders();
			for (Header h : allHeaders) {
				logger.info("Executing request {}: {}", h.getName(), h.getValue());
			}

			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					logger.info("handleResponse: {}", response.toString());
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new HttpResponseException(status, "Unexpected response status: "
								+ status);
					}
				}
			};

			try {
				String responseBody = httpclient.execute(request, responseHandler);
				logger.info("httpclient.execute responseBody: \r\n{}", responseBody);
				return responseBody;
			} catch (HttpResponseException e) {
				throw new HttpException(String.valueOf(e.getStatusCode()), e);
			} catch (IOException e) {
				throw new HttpException("请求失败", e);
			}
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("httpclient.close() error", e);
			}
		}
	}

}
