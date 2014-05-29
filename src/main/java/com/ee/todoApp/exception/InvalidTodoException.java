package com.ee.todoApp.exception;

public class InvalidTodoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidTodoException(String message) {
		super(message);
	}

}
