package com.customer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.models.dto.CustomerDTO;
import com.customer.models.solr.CustomerDocument;
import com.customer.respositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public List<CustomerDTO> getAllCustomers(String firstname, String lastname, String email, String street, String city, String state, String country)
	{
		List<CustomerDocument> customers = new ArrayList<>();	
		customerRepository
		.findByFirstnameAndLastnameAndEmailAndAddressStreetAndAddressCityAndAddressStateAndAddressCountry(firstname, lastname, email, street, city, state, country)
		.forEach(customers::add);
		return customers.stream().map(c ->convertToUserModel(c)).collect(Collectors.toList());	
	}

	public CustomerDTO addCustomer(CustomerDTO customerDto)
	{
		CustomerDocument customerDoc = convertToSolrModel(customerDto);
		customerRepository.save(customerDoc);		
		return convertToUserModel(customerDoc);		
	}
	
	public CustomerDTO getCustomerById(String id)
	{
		Optional<CustomerDocument> opt = customerRepository.findById(id);
		if( opt.isPresent())
		{
			return convertToUserModel(opt.get());
		}
		else
		{
			return null;
		}		
	}
	
	public void deleteById(String id)
	{
		customerRepository.deleteById(id);		
	}
	
	private CustomerDTO convertToUserModel(CustomerDocument c)
	{
		return modelMapper.map(c, CustomerDTO.class);
	}
	
	private CustomerDocument convertToSolrModel(CustomerDTO c)
	{	
		CustomerDocument customer = modelMapper.map(c, CustomerDocument.class);
		customer.setId(UUID.randomUUID().toString());		
		return customer;
	}
}

