package com.example.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import org.springframework.util.StringUtils;

/**
 * Created by hengxian.wang on 2017/7/21.
 */
public class Test {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService pool = Executors.newFixedThreadPool(4);

		Future<Set<String>> f1 = pool.submit(new Callable<Set<String>>() {
			@Override
			public Set<String> call() throws Exception {
				Set<String> set = new HashSet<String>();
				String f = Test.class.getClassLoader().getResource("old.txt").getFile();
				File file = new File(f);
				try (FileReader fileReader = new FileReader(file);
						BufferedReader reader = new BufferedReader(fileReader);) {
					String s = null;
					while ((s = reader.readLine()) != null) {
						set.add(s);
					}
				}
				return set;
			}
		});
		Future<Set<String>> f2 = pool.submit(new Callable<Set<String>>() {
			@Override
			public Set<String> call() throws Exception {
				Set<String> set = new HashSet<String>();
				String f = Test.class.getClassLoader().getResource("new.txt").getFile();
				File file = new File(f);
				try (FileReader fileReader = new FileReader(file);
						BufferedReader reader = new BufferedReader(fileReader);) {
					String s = null;
					while ((s = reader.readLine()) != null) {
						set.add(s);
					}
				}
				return set;
			}
		});

		// 老数据
		Set<String> o1 = f1.get();
		// 新数据
		Set<String> o2 = f2.get();
		StringBuffer stringBuffer = new StringBuffer();
		for (String s : o1) {
			if (o2.contains(s)) {
				o2.remove(s);
			} else {
				if (StringUtils.hasText(s)) {
					stringBuffer.append("'").append(s.substring(0, s.indexOf("\t"))).append("',");
				}
			}
		}

		StringBuffer s2 = new StringBuffer();
		for (String s : o2) {
			s2.append(s).append(",");
		}

		System.out.println("新数据中没有：" + stringBuffer.toString());
		System.out.println("旧数据中没有：" + s2.toString());

		pool.shutdown();
	}
}