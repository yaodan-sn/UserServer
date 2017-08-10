package com.lefu.test.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MDCLogTest {

	private static Logger logger = LoggerFactory.getLogger(MDCLogTest.class);

	public static void main(String[] args) {

		// You can put values in the MDC at any time. Before anything else
		// we put the first name
		MDC.put("first", "Dorothy");

		// We now put the last name
		MDC.put("last", "Parker");

		// The most beautiful two words in the English language according
		// to Dorothy Parker:
		logger.info("Check enclosed.");
		logger.debug("The most beautiful two words in English.");

		MDC.put("first", "Richard");
		MDC.put("last", "Nixon");
		logger.info("I am not a crook.");
		logger.info("Attributed to the former US president. 17 Nov 1973.");

	}
}
