package com.ee.todoApp.service;

import java.util.HashSet;
import java.util.Set;

import com.ee.todoApp.db.RedisDbUtil;
import com.ee.todoApp.model.Todo;

public class TodoService {
	
	public void createTodo(Todo todo) {
		RedisDbUtil.set("todo:" + todo.getId() + ":note", todo.getNote());
	}

	public Todo getTodo(final int id) {
		String note = RedisDbUtil.get("todo:" + id + ":note");
		if(note != null)
			return new Todo(id, note);
		return null;
	}

	public Set<Todo> getAllTodos() {
		Set<String> todoNoteKeys = RedisDbUtil.getKeys("todo:*:note");
		Set<Todo> allTodos = new HashSet<>();
		for (String todoNoteKey : todoNoteKeys) {
			int id = Integer.parseInt(todoNoteKey.split(":")[1]);
			String note = RedisDbUtil.get(todoNoteKey);
			allTodos.add(new Todo(id, note));
		}
		return allTodos;
	}

	public void updateTodo(Todo todo) {
		RedisDbUtil.set("todo:" + todo.getId() + ":note", todo.getNote());
	}

	public void deleteTodo(final int id) {
		RedisDbUtil.delete("todo:" + id + ":note");
	}

}
