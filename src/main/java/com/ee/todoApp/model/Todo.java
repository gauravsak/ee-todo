package com.ee.todoApp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "note" })
public class Todo {

	private int id;
	private String note;

	public Todo(int id, String note) {
		this.id = id;
		this.note = note;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	@XmlElement
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
