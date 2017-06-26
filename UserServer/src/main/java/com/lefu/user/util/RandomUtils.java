package com.lefu.user.util;

public class RandomUtils {

	public static String getRandom6bit() {
		return (int) ((Math.random() * 9 + 1) * 100000) + "";
	}

}
