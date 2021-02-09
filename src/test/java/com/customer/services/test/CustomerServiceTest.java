package com.customer.services.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.customer.models.dto.CustomerDTO;
import com.customer.models.solr.CustomerDocument;
import com.customer.respositories.CustomerRepository;
import com.customer.services.CustomerService;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerService service;
	
	@MockBean
	private CustomerRepository repository;
	
	
	
	@Test
	public void getCustomerTest() {
		when(repository.findById(Mockito.anyString())).thenReturn(createCustomers());
		Assertions.assertEquals("first",service.getCustomerById("test").getFirstname());
		
	}
			
	private Optional<CustomerDocument> createCustomers()
	{
		CustomerDocument customer = new CustomerDocument();
		customer.setFirstname("first");		
		return Optional.of(customer);
	}
	
}
