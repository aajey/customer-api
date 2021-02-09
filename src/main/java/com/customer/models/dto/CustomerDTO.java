package com.customer.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CustomerDTO {
	
	private String id;
	
	@NotNull
	private String firstname;
	
	@NotNull
	private String lastname;
	
	@Email
	private String email;
	
	@NotNull
	private AddressDTO address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	
	
	
}
