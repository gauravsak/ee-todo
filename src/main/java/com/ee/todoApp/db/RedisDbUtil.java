package com.ee.todoApp.db;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisDbUtil {
	
	private static Jedis jedis;
	
	public static void set(final String key, final String value) {
		jedis = new Jedis("localhost");
//		jedis.set("todo:" + todo.getId() + ":note", todo.getNote());
		jedis.set(key, value);
		jedis.close();
	}
	
	public static String get(final String key) {
		jedis = new Jedis("localhost");
//		Todo todo = null;
//		String note = jedis.get("todo:" + id + ":note");
//		if(note != null) {
//			todo = new Todo(id, note);
//		}
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	public static Set<String> getKeys(String wildcardPattern) {
		jedis = new Jedis("localhost");
//		Set<String> todoNoteKeys = jedis.keys("todo:*:note");
//		Set<Todo> allTodos = new HashSet<>();
//		for (String todoNoteKey : todoNoteKeys) {
//			int id = Integer.parseInt(todoNoteKey.split(":")[1]);
//			String note = jedis.get(todoNoteKey);
//			allTodos.add(new Todo(id, note));
//		}
		Set<String> keys = jedis.keys(wildcardPattern);
		jedis.close();
		return keys;
	}
	
	public static void delete(final String key) {
		jedis = new Jedis("localhost");
//		String note = jedis.get("todo:" + id + ":note");
//		if(note != null) {
//			jedis.del(note);
//		}
		String value = jedis.get(key);
		if(value != null) {
			jedis.del(key);
		}
		jedis.close();
	}
	
	public static void update(final String key, final String value) {
		jedis = new Jedis("localhost");
//		String todoNoteKey = "todo:" + todo.getId() + ":note";
//		if(jedis.get(todoNoteKey) != null) {
//			jedis.set(todoNoteKey, todo.getNote());
//		}
		if(jedis.get(key) != null) {
			jedis.set(key, value);
		}
		jedis.close();
	}

}
