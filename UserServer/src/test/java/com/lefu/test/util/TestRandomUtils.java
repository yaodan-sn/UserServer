package com.lefu.test.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lefu.util.RandomUtils;

public class TestRandomUtils {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testRandom6bit() {
		for (int i = 0; i < 100; i++) {
			logger.info(":{}", RandomUtils.getRandom6bit());
		}
	}

}
