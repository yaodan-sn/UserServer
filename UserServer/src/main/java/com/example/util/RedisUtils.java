package com.example.util;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.user.exception.RedisException;

/**
 * redis缓存工具类
 * 
 * @author dan
 *
 */
@Component
public class RedisUtils {

	protected static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

	@Autowired
	private StringRedisTemplate template;

	private static RedisUtils redis;

	public static long WEEK = 3600 * 24 * 7;
	public static long DAY = 3600 * 24;
	public static long HOUR = 3600;
	public static long MINUTE = 60;

	public static String get(String key) {
		try {
			return redis.template.boundValueOps(key).get();
		} catch (Exception e) {
			logger.error("redis取值异常！key=" + key, e);
			throw new RedisException("redis取值异常！", e);
		}
	}

	/**
	 * 从redis获取指定值
	 * 
	 * @param key
	 * @param defaultValue
	 *            当取值为空或异常时返回默认值
	 * @return
	 */
	public static String get(String key, String defaultValue) {
		try {
			String value = redis.template.boundValueOps(key).get();
			if (!StringUtils.hasText(value)) {
				return defaultValue;
			}
			return value;
		} catch (Exception e) {
			logger.error("redis取值异常！key=" + key, e);
			return defaultValue;
		}
	}

	public static void set(String key, String value) {
		try {
			redis.template.boundValueOps(key).set(value);
		} catch (Exception e) {
			logger.error("redis设置值异常！key=" + key, e);
			throw new RedisException("redis设置值异常！", e);
		}
	}

	/**
	 * 设置失效时间
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public static void set(String key, String value, long timeout) {
		try {
			BoundValueOperations<String, String> boundValueOps = redis.template.boundValueOps(key);
			boundValueOps.set(value);
			boundValueOps.expire(timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("redis设置值异常！key=" + key, e);
			throw new RedisException("redis设置值异常！", e);
		}
	}

	public static Boolean setNX(String key, String value) {
		try {
			return redis.template.boundValueOps(key).setIfAbsent(value);
		} catch (Exception e) {
			logger.error("redisNX设置值异常！key=" + key, e);
			throw new RedisException("redisNX设置值异常！", e);
		}
	}

	/**
	 * 得锁者的设置失效时间
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            业务最长处理时间
	 * @return
	 */
	public static Boolean setNX(String key, String value, long timeout) {
		try {
			BoundValueOperations<String, String> boundValueOps = redis.template.boundValueOps(key);
			Boolean setIfAbsent = boundValueOps.setIfAbsent(value);
			if (setIfAbsent) {
				boundValueOps.expire(timeout, TimeUnit.SECONDS);
			}
			return setIfAbsent;
		} catch (Exception e) {
			logger.error("redisNX设置值异常！key=" + key, e);
			throw new RedisException("redisNX设置值异常！", e);
		}
	}

	public static void expire(String key, long timeout) {
		try {
			redis.template.boundValueOps(key).expire(timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("redis Exp异常！key=" + key, e);
			throw new RedisException("redis Exp异常！", e);
		}
	}

	public static void del(String key) {
		try {
			redis.template.delete(key);
		} catch (Exception e) {
			logger.error("removeKeyValue_error: key=" + key, e);
		}
	}

	@PostConstruct
	public void init() {
		redis = this;
		redis.template = this.template;
	}

}
