package com.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

public class HelloWorld2 {

	private static Logger logger = LoggerFactory.getLogger(HelloWorld2.class);

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 10000; i++) {
			logger.trace("Hello world. trace");
			logger.debug("Hello world. debug");
			logger.info("Hello world. info");
			logger.warn("Hello world. warn");
			logger.error("Hello world. error");
			logger.error("-------------------------------");
			Thread.sleep(3000);
		}

		// print internal state
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// StatusPrinter.print(lc);
	}
}