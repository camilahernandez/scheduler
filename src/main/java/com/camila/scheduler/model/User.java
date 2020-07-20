package com.camila.scheduler.model;

import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
	
	/**
	 * 
	 */
	private UUID id;
	private String phNumber;
	private String email;
	private String firstName;
	private String lastName;
	
	
	
	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getPhNumber() {
		return phNumber;
	}



	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
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



	public User(@JsonProperty("id") UUID id,  @JsonProperty("ph") String phNumber, 
			@JsonProperty("email") String email,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName) {
		this.phNumber=phNumber;
		this.email=email;
		this.firstName=firstName;
		this.lastName=lastName;
		this.id=id;
		
	}
}
