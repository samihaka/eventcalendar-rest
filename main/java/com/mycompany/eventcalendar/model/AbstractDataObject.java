package com.mycompany.eventcalendar.model;

public abstract class AbstractDataObject {
	private int id;
	private String name;
	
	public AbstractDataObject(){
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
