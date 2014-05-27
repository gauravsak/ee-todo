package com.ee.todoApp.rest;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.ee.todoApp.model.Todo;
import com.ee.todoApp.service.TodoService;
import com.ee.todoApp.webapp.MyTodoApp;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TodoResourceTest extends JerseyTest {

	@Test
	public void createsNewTodo() {
		// Expected data
		Todo expectedTodo = new Todo(1, "set up eclipse");

		// Initialization
		TodoService todoService = new TodoService();

		// RESTful API call
		final Response response = target("rest/todo").request(
				MediaType.APPLICATION_JSON).post(
				Entity.entity(new Gson().toJson(expectedTodo),
						MediaType.APPLICATION_JSON), Response.class);

		// Assertions
		assertEquals(Status.CREATED,
				Status.fromStatusCode(response.getStatus()));

		String expectedLocation = super.getBaseUri() + "/rest/todo/"
				+ expectedTodo.getId();
		String actualLocation = response.getLocation().toString();
		assertEquals(expectedLocation, actualLocation);

		Todo createdTodo = todoService.getTodo(expectedTodo.getId());
		assertEquals(expectedTodo, createdTodo);

		// Clean up
		todoService.deleteTodo(createdTodo.getId());
	}

	@Test
	public void updatesTodo() {
		// Expected data
		Todo expectedTodo = new Todo(1, "install jenkins");

		// Initialization
		TodoService todoService = new TodoService();
		todoService.createTodo(new Todo(1, "set up eclipse"));

		// RESTful API call
		final Response response = target("rest/todo/1").request(
				MediaType.APPLICATION_JSON).put(
				Entity.entity(new Gson().toJson(expectedTodo),
						MediaType.APPLICATION_JSON), Response.class);

		// Assertions
		assertEquals(Status.OK, Status.fromStatusCode(response.getStatus()));

		Todo updatedTodo = todoService.getTodo(expectedTodo.getId());
		assertEquals(expectedTodo, updatedTodo);

		// Clean up
		todoService.deleteTodo(updatedTodo.getId());
	}

	@Test
	public void getsTodo() {
		// Expected data
		Todo expectedTodo = new Todo(1, "set up eclipse");

		// Initialization
		TodoService todoService = new TodoService();
		todoService.createTodo(expectedTodo);

		// RESTful API call
		final Response response = target("rest/todo/1").request().get(
				Response.class);

		// Assertions
		assertEquals(Status.OK, Status.fromStatusCode(response.getStatus()));

		Gson gson = new Gson();
		JsonObject expectedTodoJson = gson.fromJson(
				gson.toJson(expectedTodo), JsonObject.class);
		JsonObject actualTodoJson = gson.fromJson(
				response.readEntity(String.class), JsonObject.class);
		assertEquals(expectedTodoJson, actualTodoJson);

		// Clean up
		todoService.deleteTodo(expectedTodo.getId());
	}

	@Test
	public void getsAllTodos() {
		// Expected data
		Todo firstTodo = new Todo(1, "set up eclipse");
		Todo secondTodo = new Todo(2, "install jenkins");
		Set<Todo> expectedTodos = new HashSet<>();
		expectedTodos.add(firstTodo);
		expectedTodos.add(secondTodo);

		// Initialization
		TodoService todoService = new TodoService();
		todoService.createTodo(firstTodo);
		todoService.createTodo(secondTodo);

		// RESTful API call
		final Response response = target("rest/todos").request().get(
				Response.class);

		// Assertions
		assertEquals(Status.OK, Status.fromStatusCode(response.getStatus()));
		
		Gson gson = new Gson();
		JsonArray expectedTodosJson = gson.fromJson(
				gson.toJson(expectedTodos), JsonArray.class);
		JsonArray actualTodosJson = gson.fromJson(
				response.readEntity(String.class), JsonArray.class);
		assertEquals(expectedTodosJson, actualTodosJson);

		// Clean up
		todoService.deleteTodo(firstTodo.getId());
		todoService.deleteTodo(secondTodo.getId());
	}
	
	@Test
	public void deletesTodo() {
		// Expected data
		Todo expectedTodo = new Todo(1, "set up eclipse");

		// Initialization
		TodoService todoService = new TodoService();
		todoService.createTodo(expectedTodo);

		// RESTful API call
		final Response response = target("rest/todo/1").request().delete(
				Response.class);

		// Assertions
		assertEquals(Status.NO_CONTENT, Status.fromStatusCode(response.getStatus()));

		assertNull(todoService.getTodo(expectedTodo.getId()));
	}

	@Override
	protected Application configure() {
		return new MyTodoApp();
	}

	@Override
	protected URI getBaseUri() {
		return UriBuilder.fromUri(super.getBaseUri()).path("my-todo-app")
				.build();
	}

}
