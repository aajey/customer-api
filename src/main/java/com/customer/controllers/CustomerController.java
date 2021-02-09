package com.customer.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.customer.models.clients.CustomerDTO;
import com.customer.respositories.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getCustomers(
			@RequestParam(value="firstname", defaultValue = "*") String firstname,
			@RequestParam(value= "lastname", defaultValue= "*") String lastname,
			@RequestParam(value= "email", defaultValue="*") String email,
			@RequestParam(value= "street", defaultValue="*") String street,
			@RequestParam(value= "city", defaultValue="*") String city,
			@RequestParam(value= "state", defaultValue="*") String state,
			@RequestParam(value= "country", defaultValue="*") String country
			) {		
		return new ResponseEntity<List<CustomerDTO>>(customerService.getAllCustomers(firstname, lastname, email, street, city, state, country), HttpStatus.OK);
	}
	
	
	@GetMapping(path="/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String id) {		
		return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer( @Valid @RequestBody CustomerDTO customer) throws Exception
	{	
		if(customer.getFirstname()==null)
			throw new Exception("testing");
		customerService.addCustomer(customer);	
		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteCustomer(@RequestBody String customerId)
	{
		//customers.remove(customerId);
	}

}
