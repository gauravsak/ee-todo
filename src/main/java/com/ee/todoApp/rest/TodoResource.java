package com.ee.todoApp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ee.todoApp.model.Todo;
import com.ee.todoApp.service.TodoService;

@Path("/rest")
public class TodoResource {

	@Context
	private UriInfo uriInfo;

	@POST
	@Path("/todo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTodo(Todo todo) {
		TodoService todoService = new TodoService();
		todoService.createTodo(todo);
		return Response.created(
				uriInfo.getAbsolutePathBuilder().path("" + todo.getId())
						.build()).build();
	}
	
	@PUT
	@Path("/todo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTodo(@PathParam("id") int id, Todo todo) {
		TodoService todoService = new TodoService();
		todoService.updateTodo(todo);
		return Response.ok(todo).build();
	}

	@GET
	@Path("/todo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodo(@PathParam("id") int id) {
		TodoService todoService = new TodoService();
		return Response.ok(todoService.getTodo(id)).build();
	}
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTodos() {
		TodoService todoService = new TodoService();
		return Response.ok(todoService.getAllTodos()).build();
	}
	
	@DELETE
	@Path("/todo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTodo(@PathParam("id") int id) {
		TodoService todoService = new TodoService();
		todoService.deleteTodo(id);
		return Response.noContent().build();
	}
}
