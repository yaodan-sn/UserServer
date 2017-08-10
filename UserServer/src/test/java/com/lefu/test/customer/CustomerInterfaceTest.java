package com.lefu.test.customer;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.lefu.test.common.WebAppContextSetupTest;
import com.pay.cust.dubbo.CustomerInterface;
import com.pay.cust.entity.Customer;

public class CustomerInterfaceTest extends WebAppContextSetupTest {

	@Resource
	private CustomerInterface customerInterface;

	@Test
	public void testUpdate() {
		Customer customer = new Customer();
		customer.setCustomerNo("86191338319");
		customer.setFullName("13123132131");
		customer.setStatus("TRUE");
		String update = customerInterface.update(customer, "123123", "信息修改", "N");
		logger.info(update);
	}

	@Test
	public void testCustomerQuery() throws Exception {
		LongAdder longSuccessTimes = new LongAdder();

		LongAdder longFailTimes = new LongAdder();

		LongAdder longMills = new LongAdder();

		int cycleTimes = 1000;
		
		List<String> list = FileUtils.readLines(new File(
				"E:/git_repository/UserServer/src/test/java/customer_no.txt"));
		
		long begin = System.currentTimeMillis();
		int size = 200;
		CountDownLatch countDownLatch = new CountDownLatch(size);
		for (int i = 0; i < size; i++) {
			new Thread(new CustomerQuery(longSuccessTimes, longFailTimes, longMills, countDownLatch, list))
					.start();
		}
		countDownLatch.await();

		long end = System.currentTimeMillis() - begin;

		logger.info("线程数:{}-执行次数:{}-成功次数:{}-失败次数:{}-平均响应:{}ms-总耗时:{}ms", size, size * cycleTimes,
				longSuccessTimes.longValue(), longFailTimes.longValue(), longMills.longValue() / (size * cycleTimes),
				end);

	}

	class CustomerQuery implements Runnable {

		private LongAdder longSuccessTimes;

		private LongAdder longFailTimes;

		private LongAdder longMills;

		private CountDownLatch countDownLatch;
		
		private List<String> listCustomer;

		private int cycleTimes;


		public CustomerQuery(LongAdder longSuccessTimes, LongAdder longFailTimes,
				LongAdder longMills, CountDownLatch countDownLatch, List<String> listCustomer) {
			super();
			this.longSuccessTimes = longSuccessTimes;
			this.longFailTimes = longFailTimes;
			this.longMills = longMills;
			this.countDownLatch = countDownLatch;
			this.listCustomer = listCustomer;
			this.cycleTimes = listCustomer.size();
		}



		@Override
		public void run() {
			for (int i = 0; i < cycleTimes; i++) {
				try {
					long begin = System.currentTimeMillis();
					Customer c = customerInterface.findByCustomerNo(listCustomer.get(i));
					longMills.add(System.currentTimeMillis() - begin);
					logger.info("findByCustomerNo {}", c);
					longSuccessTimes.increment();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					longFailTimes.increment();
				}
			}
			countDownLatch.countDown();
		}
	}

}
