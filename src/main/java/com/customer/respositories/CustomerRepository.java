package com.customer.respositories;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.customer.models.solr.CustomerDocument;

public interface CustomerRepository extends SolrCrudRepository<CustomerDocument, String> {
	
	public List<CustomerDocument> findByFirstnameAndLastnameAndEmailAndAddressStreetAndAddressCityAndAddressStateAndAddressCountry(String firstname, String lastname, String email, String street, String city, String state, String country);
}
