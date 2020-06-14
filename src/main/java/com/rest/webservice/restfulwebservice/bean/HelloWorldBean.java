package com.rest.webservice.restfulwebservice.bean;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	/*
	 * If getter not defined for the field, then while returning HelloWorldBean gives error message:
	 * No converter found for return value of type: class com.rest.webservice.restfulwebservice.bean.HelloWorldBean
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
}
