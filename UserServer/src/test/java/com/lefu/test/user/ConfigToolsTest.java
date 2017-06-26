package com.lefu.test.user;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;

public class ConfigToolsTest {

	@Test
	public void encodeTest() throws Exception {
		ConfigTools.main(new String[] { "haoshikisses" });
	}
}
