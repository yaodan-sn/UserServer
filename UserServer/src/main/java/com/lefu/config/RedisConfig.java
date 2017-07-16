package com.lefu.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author dan
 *
 */
@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {

	@Value("${spring.redis.sentinel.master:}")
	private String master;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.database}")
	private int database;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;

	@Value("${spring.redis.pool.max-active}")
	private int maxActive;

	@Value("${spring.redis.pool.max-wait}")
	private int maxWait;

	@Bean
	public RedisSentinelConfiguration redisSentinelConfiguration() throws IOException {
		ResourcePropertySource propertySource = new ResourcePropertySource("redis",
				"classpath:redis.properties");
		RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(propertySource);
		return sentinelConfig;
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWait);
		jedisPoolConfig.setMaxTotal(maxActive);
		return jedisPoolConfig;
	}

	@Bean
	public RedisConnectionFactory jedisConnectionFactory(RedisSentinelConfiguration sentinelConfig,
			JedisPoolConfig poolConfig) {

		JedisConnectionFactory factory = null;
		if (StringUtils.hasText(master)) {
			factory = new JedisConnectionFactory(sentinelConfig, poolConfig);
		} else {
			factory = new JedisConnectionFactory(poolConfig);
			factory.setHostName(host);
			factory.setPort(port);
		}
		factory.setDatabase(database);
		factory.setPassword(password);
		factory.setTimeout(timeout);
		return factory;
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(connectionFactory);
		return stringRedisTemplate;
	}

}