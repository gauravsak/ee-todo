package com.ee.todoApp.db;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisDbUtil {
	
	private static Jedis jedis;
	
	public static void set(final String key, final String value) {
		jedis = new Jedis("localhost");
		jedis.set(key, value);
		jedis.close();
	}
	
	public static String get(final String key) {
		jedis = new Jedis("localhost");
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	public static Set<String> getKeys(String wildcardPattern) {
		jedis = new Jedis("localhost");
		Set<String> keys = jedis.keys(wildcardPattern);
		jedis.close();
		return keys;
	}
	
	public static void delete(final String key) {
		jedis = new Jedis("localhost");
		String value = jedis.get(key);
		if(value != null) {
			jedis.del(key);
		}
		jedis.close();
	}
	
	public static void update(final String key, final String value) {
		jedis = new Jedis("localhost");
		if(jedis.get(key) != null) {
			jedis.set(key, value);
		}
		jedis.close();
	}

}
