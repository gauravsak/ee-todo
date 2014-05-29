package com.ee.todoApp.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.ee.todoApp.db.RedisDbUtil;
import com.ee.todoApp.exception.InvalidTodoException;
import com.ee.todoApp.model.Todo;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ RedisDbUtil.class })
public class TodoServiceTest {

	private TodoService todoService;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() {
		PowerMockito.mockStatic(RedisDbUtil.class);
		todoService = new TodoService();
	}

	@Test
	public void createsNewTodo() {
		when(RedisDbUtil.get("todo:1:note")).thenReturn("set up eclipse");

		Todo todo = new Todo(1, "set up eclipse");
		todoService.createTodo(todo);

		assertEquals(todo, todoService.getTodo(1));
	}
	
	@Test
	public void findsTodo() {
		when(RedisDbUtil.get("todo:1:note")).thenReturn("set up eclipse");

		Todo todo = new Todo(1, "set up eclipse");

		assertEquals(todo, todoService.getTodo(1));
	}
	
	@Test
	public void findsAllTodos() {
		Set<Todo> allTodos = new HashSet<>();
		
		Todo firstTodo = new Todo(1, "set up eclipse");
		allTodos.add(firstTodo);
		Todo secondTodo = new Todo(2, "install jenkins");
		allTodos.add(secondTodo);
		
		Set<String> todoNoteKeys = new HashSet<>();
		todoNoteKeys.add("todo:1:note");
		todoNoteKeys.add("todo:2:note");
		when(RedisDbUtil.getKeys("todo:*:note")).thenReturn(todoNoteKeys);
		
		when(RedisDbUtil.get("todo:1:note")).thenReturn("set up eclipse");
		when(RedisDbUtil.get("todo:2:note")).thenReturn("install jenkins");

		assertEquals(allTodos, todoService.getAllTodos());
	}
	
	@Test
	public void updatesTodo() {
		when(RedisDbUtil.get("todo:1:note")).thenReturn("install gradle");

		Todo todo = new Todo(1, "install gradle");
		todoService.updateTodo(todo);

		assertEquals(todo, todoService.getTodo(1));
	}
	
	@Test
	public void deletesTodo() {
		when(RedisDbUtil.get("todo:1:note")).thenReturn(null);

		todoService.deleteTodo(1);

		assertNull(todoService.getTodo(1));
	}
	
	@Test
	public void throwsInvalidTodoExceptionifTodoNoteEmpty() {
		
	}
}
