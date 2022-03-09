package com.cognixia.jump;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.controller.RetailController;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.BookSale;

import com.cognixia.jump.service.RetailService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RetailController.class)
class RetailAppTests {
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RetailService serv;
	
	@InjectMocks
	private RetailController controller;
	
	@Test
	void testGetAllBooks()  throws Exception {
		String uri = STARTING_URI + "/books";

		List<Book> books = Arrays.asList( 
				new Book("Book1", "Author1", 2.2, 100, 5), 
				new Book("Book2", "Author2", 2.2, 100, 5));
		
		when( serv.getAllBooks() ).thenReturn(books);
		
		mvc.perform( get(uri) ) // perform request...
        .andDo( print() )   // ...then print request sent and response returned
        .andExpect( status().isOk() ) // expect 200 status code
        .andExpect( jsonPath("$.length()").value(books.size() ) )
        .andExpect( jsonPath("$[0].id").value( books.get(0).getId() ) )
		.andExpect( jsonPath("$[0].author").value( books.get(0).getAuthor() ) )
		.andExpect( jsonPath("$[0].title").value( books.get(0).getTitle() ) )
		.andExpect( jsonPath("$[1].id").value( books.get(1).getId() ) )
		.andExpect( jsonPath("$[1].author").value( books.get(1).getAuthor() ) )
		.andExpect( jsonPath("$[1].title").value( books.get(1).getTitle() ) );
	}
	
	@Test 
	void testGetBookByTitle() throws Exception {
		String uri = STARTING_URI + "/books/{title}";
		String title = "Book1";
		Optional<Book> boo = Optional.of(new Book("Book1", "Author1", 2.2, 100, 5));
		
		when( serv.getBookByTitle("Book1") ).thenReturn(boo);
		
		mvc.perform( get(uri, title) ) // perform request...
        .andDo( print() )   // ...then print request sent and response returned
        .andExpect( status().isOk() ) // expect 200 status code
        .andExpect( jsonPath("$.id").value( boo.get().getId() ) )
		.andExpect( jsonPath("$.author").value( boo.get().getAuthor() ) )
		.andExpect( jsonPath("$.title").value( boo.get().getTitle() ) );
		
	}
}
