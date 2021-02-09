package com.customer;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.customer.models.dto.AddressDTO;
import com.customer.models.dto.CustomerDTO;
import com.customer.services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomerService service;
	
		
	@Test
	public void getCustomers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/customers/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}
	
	
	@Test
	public void getCustomer() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/customers/testing").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("")));
	}
	
	@Test
	public void deleteCustomer() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/customers/{id}", "testing").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent())
				.andExpect(content().string(equalTo("")));
	}
	
	private String asJsonString(CustomerDTO c)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
		  String json = mapper.writeValueAsString(c);
		  return json;
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		   return null;
		}
	}
	
	@Test
	public void postCustomerAllNullValues() throws Exception {
		
		CustomerDTO c = new CustomerDTO();
		String customerC = asJsonString(c);
		
		mvc.perform(MockMvcRequestBuilders.post("/customers")
				.content(customerC)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void postCustomerNotValidEmail() throws Exception {
		
		CustomerDTO c = createCustomer();
		c.setEmail("notemail");
		String customerC = asJsonString(c);
		
		mvc.perform(MockMvcRequestBuilders.post("/customers")
				.content(customerC)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void postCustomerMissingFirstname() throws Exception {
		
		CustomerDTO c = createCustomer();
		c.setFirstname(null);
		String customerC = asJsonString(c);
		
		mvc.perform(MockMvcRequestBuilders.post("/customers")
				.content(customerC)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void postCustomerValid() throws Exception {
		
		CustomerDTO c = createCustomer();
		
		String customerC = asJsonString(c);
		
		mvc.perform(MockMvcRequestBuilders.post("/customers")
				.content(customerC)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	
	private CustomerDTO createCustomer()
	{
		CustomerDTO c = new CustomerDTO();
		c.setFirstname("first");
		c.setLastname("last");
		c.setEmail("first@g.com");
		AddressDTO addr = new AddressDTO();
		addr.setStreet("street");
		addr.setCity("city");
		addr.setState("state");
		addr.setCountry("country");
		c.setAddress(addr);
		return c;
	}
}
