package com.example.demo.model;

import java.util.Date;

import lombok.ToString;

@ToString
public class Account {

	private Integer id;
	private String firstName;
	private String lastName;
	private String name;
	private Date creationDate;
	private String type;
	
	

	public Account() {
		super();
	}

	public Account(Integer id, String firstName, String lastName, String name, Date creationDate, String type) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = name;
		this.creationDate = creationDate;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
