package com.lefu.test.user.service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DataAccessException;

import com.lefu.test.common.WebAppContextSetupTest;
import com.lefu.user.constant.IncrementerConstant;
import com.lefu.user.service.IncrementerService;

public class IncrementerServiceTest extends WebAppContextSetupTest {

	@Resource
	private IncrementerService incrementerService;

	@Test
	public void testNextLongValue() throws InterruptedException, IOException {
		long start = System.currentTimeMillis();
		CountDownLatch countDownLatch = new CountDownLatch(100);
		for (int i = 0; i < 10; i++) {
			new Thread(new NextLongValue()).start();
		}
		countDownLatch.await();
		logger.info("end: {} ms", System.currentTimeMillis() - start);

	}

	class NextLongValue implements Runnable {

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++) {
				try {
					logger.info("get nextLong {}",
							incrementerService.nextLongValue(IncrementerConstant.CUSTOMER_NO));
				} catch (DataAccessException e) {
					i--;
					logger.error(e.getMessage());
				}
			}
			logger.info(": {} ms", System.currentTimeMillis() - start);
		}

	}

}
