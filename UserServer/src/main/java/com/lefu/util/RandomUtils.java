package com.lefu.util;

public class RandomUtils {

	public static String getRandom6bit() {
		return Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
	}

}
