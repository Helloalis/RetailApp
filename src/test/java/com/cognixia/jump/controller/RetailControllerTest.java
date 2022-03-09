package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.service.RetailService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RetailController.class)
public class RetailControllerTest {

	
	private final String STARTING_URI = "http://localhost:8080/api";
	
	// mocks the http request/response
	@Autowired
	private MockMvc mvc;
	
	// when methods from the service are called within the controller
	// we want to decide what data gets returned from those methods instead of actually
	// running that method
	@MockBean
	private RetailService service;
	
	// when controller tries to autowire the service, mock the creation of the service,
	// don't actually create a proper service object (mock service object created instead)
	@InjectMocks
	private RetailController controller;
	
	@Test
	void testGetUser() throws Exception {
		
		// Arrange, Act, Assert
		
		// ARRANGE -> set up what we need to do the testing
		String name = "admin";
		String uri = "/api/user/" + name;
		
		// mockito will create a request object that will make its
		// request to our uri above
		RequestBuilder request = MockMvcRequestBuilders.get(uri);
		
		
		// ACT -> perform the action that we will test
		
		// perform the api call/request and return the response
		MvcResult result = mvc.perform(request).andReturn();
		
		
		// ASSERT -> make sure our result is as expected
		
		// The body contains the message: "Hello Peppa"
	
			
	}
	
	
	
	
}












