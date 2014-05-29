package com.ee.todoApp.exception.mapper;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ee.todoApp.exception.InvalidTodoException;

@Provider
public class InvalidTodoMapper implements ExceptionMapper<InvalidTodoException> {
	
	@Override
	public Response toResponse(InvalidTodoException exception) {
		Map<String, String> exceptionResponseMap = new HashMap<>();
		exceptionResponseMap.put("exceptionType", exception.getClass().getName());
		exceptionResponseMap.put("message", exception.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(exceptionResponseMap).build();
	}
}
