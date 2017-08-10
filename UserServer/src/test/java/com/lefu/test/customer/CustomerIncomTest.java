package com.lefu.test.customer;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.lefu.util.HttpUtil;

public class CustomerIncomTest {

	private static Logger logger = LoggerFactory.getLogger(CustomerIncomTest.class);

//	private static String CUST_SERVER_URL = "http://10.10.129.25:8085/custserver";
	private static String CUST_SERVER_URL = "http://10.10.111.136:8085/custserver";
	private static String MASTER_KEY = "QuVcpeK2liQ=";

	@Test
	public void testSaveCustomer() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("acceptKey", "aTrtmsyhxlA");
		param.put("timeStamp", Long.toString(System.currentTimeMillis()));
		param.put("customerNo", "86301953522");
		param.put("agentNo", "186");
		param.put("agentPhone", "18730643650");

		param.put("customerType", "DLB");
		param.put("shortName", "孙氏集团123123456");
		param.put("printName", "孙氏集团234234");

		param.put("phoneNo", "18301310621");

		param.put("province", "北京");
		param.put("city", "市辖区");

		param.put("receiveAddress", "山西省临汾市");
		param.put("companyType", "B");
		param.put("identityNo", "429005198910075234");
		param.put("identityAddress", "山西省临汾市天上人间");
		param.put("legalPerson", "孙小宝");
		param.put("identityNo", "429005198910075234");

		String makeStringArraySign = makeStringArraySign(param, MASTER_KEY);

		param.put("sign", makeStringArraySign);
		HttpUtil.post(CUST_SERVER_URL + "/income/baseinfo/save.json", null, param);
	}

	@Test
	public void testSaveImg() throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("acceptKey", "aTrtmsyhxlA");
		param.put("timeStamp", Long.toString(System.currentTimeMillis()));

		byte[] binaryData = FileUtils.readFileToByteArray(new File("d:/logo.png"));
		String value = Base64.encodeBase64String(binaryData);

		param.put("customerNo", "86301953522");
		param.put("holderImg", value);
		param.put("backImg", value);
		param.put("bussinesImg", value);

		String makeStringArraySign = makeStringArraySign(param, MASTER_KEY);

		param.put("sign", makeStringArraySign);
		HttpUtil.post(CUST_SERVER_URL + "/income/bussimg/save.json", null, param);
	}

	@Test
	public void testSaveSettletCard() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("acceptKey", "aTrtmsyhxlA");
		param.put("timeStamp", Long.toString(System.currentTimeMillis()));
		param.put("customerNo", "86301953522");
		param.put("uploadCardType", "INDIVIDUAL");
		param.put("privateAccountName", "贾贺钊");
		param.put("privateAccountNo", "6228481648175283378");
		param.put("privateBankCode", "ABC");
		param.put("privateBankName", "中国农业银行股份有限公司阳泉市分行");
		param.put("privateAlliedBankCode", "103163080001");
		param.put("privateSabkCode", "103100000026");
		param.put("privateProvince", "山西");
		param.put("privateCity", "阳泉市");

		String makeStringArraySign = makeStringArraySign(param, MASTER_KEY);

		param.put("sign", makeStringArraySign);
		HttpUtil.post(CUST_SERVER_URL + "/income/account/save.json", null, param);
	}

	@Test
	public void testSaveCustomerFee() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("acceptKey", "aTrtmsyhxlA");
		param.put("timeStamp", Long.toString(System.currentTimeMillis()));
		param.put("customerNo", "86301953522");
		param.put("productType", "MD");
		param.put("creditRate", "0.0072");
		param.put("debitRate", "0.0072");
		param.put("scanRate", "0.005");
		param.put("custType", "STANDARD");
		param.put("extAtt", "W");

		String makeStringArraySign = makeStringArraySign(param, MASTER_KEY);

		param.put("sign", makeStringArraySign);
		HttpUtil.post(CUST_SERVER_URL + "/income/product/save.json", null, param);

	}

	public static String makeStringArraySign(Map<String, String> map, String secretKey) {
		String partnerString = "";
		ArrayList<String> paramNames = new ArrayList<String>(map.keySet());
		Collections.sort(paramNames);
		StringBuilder signSource = new StringBuilder();
		Iterator<String> iterator = paramNames.iterator();
		while (iterator.hasNext()) {
			String paramName = iterator.next();
			if (null != map.get(paramName)) {
				String value = map.get(paramName);

				if (StringUtils.hasText(value) && !"sign".equals(value)) {
					signSource.append(paramName).append("=").append(value);
					if (iterator.hasNext()) {
						signSource.append("&");
					}
				}
			}
		}
		partnerString = signSource.toString();
		partnerString += secretKey;
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(partnerString.getBytes(Charset
				.forName("UTF-8")));
		logger.info("partnerString={}, md5={}", partnerString, md5DigestAsHex);
		return md5DigestAsHex;
	}
}
