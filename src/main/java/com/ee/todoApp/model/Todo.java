package com.ee.todoApp.model;

public class Todo {

	private int id;
	private String note;
	
	private Todo() {}

	public Todo(int id, String note) {
		this.id = id;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public String getNote() {
		return note;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Todo) obj).id;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public String toString() {
		return "[ " + id + ", " + note + " ]";
	}

}
