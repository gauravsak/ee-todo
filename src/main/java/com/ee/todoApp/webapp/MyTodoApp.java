package com.ee.todoApp.webapp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.ee.todoApp.exception.mapper.InvalidTodoMapper;
import com.ee.todoApp.rest.TodoResource;


@ApplicationPath("/")
public class MyTodoApp extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		
		// Resources
		classes.add(TodoResource.class);
		
		//Providers
		classes.add(InvalidTodoMapper.class);
		
		//Features
		classes.add(JacksonFeature.class);
		
		return classes;
	}
}
