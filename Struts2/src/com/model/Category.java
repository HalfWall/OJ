package com.model;

public class Category {
	private int id;
	private String name;
	private String context;
	private String testIn;
	private String testOut;
	public String getTestIn() {
		return testIn;
	}
	public void setTestIn(String testIn) {
		this.testIn = testIn;
	}
	public String getTestOut() {
		return testOut;
	}
	public void setTestOut(String testOut) {
		this.testOut = testOut;
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	

}
