package com.customer.models.solr;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "customers")
public class CustomerDocument {
	@Id
	@Indexed(name = "id", type = "String")
	private String id;

	@Indexed(name = "firstname", type = "String")
	private String firstname;

	@Indexed(name = "lastname", type = "String")
	private String lastname;

	@Indexed(name = "email", type = "String")
	private String email;

	@Indexed(name = "addressStreet", type = "String")
	private String addressStreet;

	@Indexed(name = "addressCountry", type = "String")
	private String addressCountry;

	@Indexed(name = "addressState", type = "String")
	private String addressState;

	@Indexed(name = "addressCity", type = "String")
	private String addressCity;

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String city) {
		this.addressCity = city;
	}

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
}
