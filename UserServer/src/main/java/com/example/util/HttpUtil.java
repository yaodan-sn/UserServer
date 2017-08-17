package com.example.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.user.exception.HttpException;

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
		return httpRequest(url, HttpPost.METHOD_NAME, null, null);
	}

	public static String patch(String url) {
		return httpRequest(url, HttpPatch.METHOD_NAME, null, null);
	}

	public static String patch(String url, Map<String, String> header, Map<String, String> param) {
		return httpRequest(url, HttpPatch.METHOD_NAME, header, param);
	}

	public static String get(String url) {
		return httpRequest(url, HttpGet.METHOD_NAME, null, null);
	}

	public static String get(String url, Map<String, String> header, Map<String, String> param) {
		return httpRequest(url, HttpGet.METHOD_NAME, header, param);
	}

	public static String post(String url, Map<String, String> header, Map<String, String> param) {
		return httpRequest(url, HttpPost.METHOD_NAME, header, param);
	}

	public static String download(String url, String filepath) {
		FileOutputStream fileout = null;
		InputStream is = null;
		try {
			CookieStore cookieStore  = new BasicCookieStore();;
			BasicClientCookie cookie1 = new BasicClientCookie("_gitlab_session", "2e8bfedafee311c61ab5a7c7ba6eb3a2");
			cookie1.setDomain("118.190.76.47");
			BasicClientCookie cookie2 = new BasicClientCookie("td_cookie", "469180382");
			cookie2.setDomain("118.190.76.47");
			cookieStore.addCookie(cookie1);
			cookieStore.addCookie(cookie2);
			CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			File file = new File(filepath, getFileName(response));

			file.getParentFile().mkdirs();
			fileout = new FileOutputStream(file);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer = new byte[10 * 1024];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				logger.info("size={}", ch);
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();
			logger.info("{}下载完成", url);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("{}下载失败", url);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(fileout);
		}
		return null;
	}

	public static String getFileName(HttpResponse response) {
		logger.info("{}", response);
		Header contentHeader = response.getFirstHeader("Content-Disposition");
		String filename = null;
		if (contentHeader != null) {
			HeaderElement[] values = contentHeader.getElements();
			if (values.length == 1) {
				NameValuePair param = values[0].getParameterByName("filename");
				if (param != null) {
					try {
						// filename = new
						// String(param.getValue().toString().getBytes(),
						// "utf-8");
						// filename=URLDecoder.decode(param.getValue(),"utf-8");
						filename = param.getValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return filename;
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
				requestBuilder = RequestBuilder.get(url);
			} else if (HttpPost.METHOD_NAME.equals(methodName)) {
				requestBuilder = RequestBuilder.post(url);
			} else if (HttpPatch.METHOD_NAME.equals(methodName)) {
				requestBuilder = RequestBuilder.patch(url);
			} else {
				requestBuilder = RequestBuilder.get(url);
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
