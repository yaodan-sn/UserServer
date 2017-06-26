package com.lefu.test.user;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.lefu.user.util.HttpUtil;

public class HttpUtilTest {

	@Test
	public void test2() throws IOException, InterruptedException {
		Random random = new Random();
		List<String> readLines = FileUtils.readLines(new File("d:/open.txt"), "UTF-8");
		for (String string : readLines) {
			if (StringUtils.hasText(string)) {
				HttpUtil.post(string);
				Thread.sleep(random.nextInt(500));
			}
		}
	}

	@Test
	public void test() {
		HttpUtil.post("");
	}

	@Test
	public void postTest() throws Exception {
		String url = "https://192.168.16.165:8443/oauth2/authorize";
		Map<String, String> param = new HashMap<String, String>();
		param.put("client_id", "e295247ca38449df886fda7097e68d7d");
		param.put("response_type", "code");
		param.put("scope", "email address");
		param.put("provision_key", "b2499f433b0341819e477caa2b05096b");
		param.put("authenticated_userid", "zhang.san");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "test.com");
		HttpUtil.post(url, header, param);
	}

	@Test
	public void testCustServer() {
		String url = "http://192.168.1.103:8000/";

		Map<String, String> param = null;
		Map<String, String> header = new HashMap<String, String>();
		header.put("host", "192.168.1.103");
		HttpUtil.get(url, header, param);

	}
}
