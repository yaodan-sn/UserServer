package com.example.test.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.util.RandomUtils;

public class TestRandomUtils {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testRandom6bit() {
		logger.info(":{}", RandomUtils.getRandom6bit());
	}

}
