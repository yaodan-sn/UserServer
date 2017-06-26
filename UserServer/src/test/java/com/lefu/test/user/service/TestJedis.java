package com.lefu.test.user.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import com.lefu.test.common.WebAppContextSetupTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJedis extends WebAppContextSetupTest {

	@Resource
	public JedisConnectionFactory jedisConnetionFactory;

	public JedisConnection jedisConnection;

	@Resource
	public StringRedisTemplate redisTemplate;

	@Before
	public void setBefore() {
		jedisConnection = (JedisConnection) jedisConnetionFactory.getConnection();
	}

	@After
	public void setAfter() {
		jedisConnection.close();
	}

	private void print(Collection<RedisServer> c) {
		for (Iterator<RedisServer> iter = c.iterator(); iter.hasNext();) {
			RedisServer rs = (RedisServer) iter.next();
			System.out.println(rs.getHost() + ":" + rs.getPort());
		}
	}

	// 简单测试JedisConnection
	@Ignore
	@Test
	public void test1() {
		if (!jedisConnection.exists(new String("zz").getBytes())) {
			jedisConnection.set(new String("zz").getBytes(), new String("zz").getBytes());
		}
	}

	@Test
	public void test2() {
		Set<byte[]> keys = jedisConnection.keys(new String("*").getBytes());
		for (Iterator<byte[]> iter = keys.iterator(); iter.hasNext();) {
			System.out.println(new String(iter.next()));
		}
	}

	// 测试Sentinel
	@Test
	public void test3() throws InterruptedException {
		if (jedisConnetionFactory.getSentinelConnection().isOpen()) {
			Collection<RedisServer> c = jedisConnetionFactory.getSentinelConnection().masters();
			print(c);
			RedisNode rn = new RedisNode("10.10.111.90", 6378);
			rn.setName("mymaster");
			c = jedisConnetionFactory.getSentinelConnection().slaves(rn);
			print(c);
		}

		for (int i = 0; i < 1000; i++) {
			jedisConnection.set(new String("k" + i).getBytes(), new String("v" + i).getBytes());
		}
		Set<byte[]> keys = jedisConnection.keys(new String("k*").getBytes());
		Assert.isTrue(1000 == keys.size(), "key值不相等");
	}

	// 测试RedisTemplate,自主处理key的可读性(String序列号)
	@Ignore
	@Test
	public void test4() {
		String key = "spring";
		ListOperations<String, String> lop = redisTemplate.opsForList();
		RedisSerializer<String> serializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(serializer);
		redisTemplate.setValueSerializer(serializer);
		// rt.setDefaultSerializer(serializer);

		lop.leftPush(key, "aaa");
		lop.leftPush(key, "bbb");
		long size = lop.size(key); // rt.boundListOps(key).size();
		Assert.isTrue(2 == size, "error");
	}

	// 测试便捷对象StringRedisTemplate
	@Test
	public void test5() {
		ValueOperations<String, String> vop = redisTemplate.opsForValue();
		String key = "string_redis_template";
		String v = "use StringRedisTemplate set k v";
		vop.set(key, v);
		String value = vop.get(key);
		Assert.isTrue(v.equals(value), "error");
	}
}