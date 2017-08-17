package com.example.test.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.example.util.HttpUtil;

public class HttpUtilTest {

	@Test
	public void downloadFile() throws IOException {

		/*List<String> readLines = FileUtils.readLines(new File(
				"E:/git_repository/UserServer/src/test/java/customer_no.txt"));
		for (String string : readLines) {
			if (!StringUtils.hasText(string)) {
				return;
			}
			String[] split = string.split("-");
			String url = "https://v.lefu8.com/custserver/customer/contractlog/addCustomerContractLog.json?customerNo="
					+ split[1];
			HttpUtil.download(url, "d:/download/" + split[0]);
		}*/
		
		HttpUtil.download("http://118.190.76.47/apollo/invoice-cloud/repository/archive.zip?ref=master", "d:/download/1231233");

	}
	
	
	@Test
	public void downloadFile2() throws IOException {

		List<String> readLines = FileUtils.readLines(new File(
				"E:/git_repository/UserServer/src/test/java/customer_no.txt"));
		
		
		Set<String> set = new HashSet<String>();
		
		File file = new File("D:/download");
		File[] listFiles = file.listFiles();
		
		for (File f : listFiles) {
			File[] ff = f.listFiles();
			for (File fff : ff) {
				set.add(fff.getName().substring(0, fff.getName().indexOf(".")));
			}
		}
		System.out.println(set.size());
		for (String ss : readLines) {
			if(StringUtils.hasText(ss)){
				if(!set.contains(ss.split("-")[1])){
					System.out.println(ss.split("-")[1]);
				}
			}
		}

	}

	@Test
	public void testpost() throws IOException, InterruptedException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("code", "562481");
		param.put("type", "UPD");
		param.put(
				"param",
				"update task_job set status='check' where id in (540292,7892465341,7892465365,7892465366,7892465367,7892465368,7892465369,7892465370,7892465371,7892465372,7892465373,7892465374,7892465375)");
		HttpUtil.patch("http://10.10.129.25:8085/custserver/switch/execute.json", null, param);
	}

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
