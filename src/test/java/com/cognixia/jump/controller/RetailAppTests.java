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
import com.cognixia.jump.model.Sale;

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
	
	@Test
	void testGetBooksByAuthor()  throws Exception {
		String uri = STARTING_URI + "/books/author/Author1";

		List<Book> books = Arrays.asList( 
				new Book("Book1", "Author1", 2.2, 100, 5), 
				new Book("Book2", "Author1", 2.2, 100, 5));
		
		when( serv.getBookByAuthor("Author1") ).thenReturn(books);
		
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
//		.andExpect( jsonPath("$[0].author").value( jsonPath("$[1].author")));
	}
	
	@Test
	void testGetBooksLarger() throws Exception {
		String uri = STARTING_URI + "/books/longer/99";
		List<Book> books = Arrays.asList( 
				new Book("Book1", "Author1", 2.2, 120, 5), 
				new Book("Book2", "Author2", 2.2, 100, 5));
		when(serv.getBySizeGreaterThan(99)).thenReturn(books);
		
		mvc.perform( get(uri) ) // perform request...
        .andDo( print() )   // ...then print request sent and response returned
        .andExpect( status().isOk() ) // expect 200 status code
        .andExpect( jsonPath("$.length()").value(books.size() ) )
        .andExpect( jsonPath("$[0].id").value( books.get(0).getId() ) )
		.andExpect( jsonPath("$[0].author").value( books.get(0).getAuthor() ) )
		.andExpect( jsonPath("$[0].size").value( books.get(0).getSize() ) )
		.andExpect( jsonPath("$[1].id").value( books.get(1).getId() ) )
		.andExpect( jsonPath("$[1].author").value( books.get(1).getAuthor() ) )
		.andExpect( jsonPath("$[1].size").value( books.get(1).getSize() ) );
		
		
	}
	
	@Test
	void testGetBooksShorter() throws Exception {
		String uri = STARTING_URI + "/books/shorter/150";
		List<Book> books = Arrays.asList( 
				new Book("Book1", "Author1", 2.2, 120, 5), 
				new Book("Book2", "Author2", 2.2, 100, 5));
		when(serv.getBySizeLessThan(150)).thenReturn(books);
		
		mvc.perform( get(uri) ) // perform request...
        .andDo( print() )   // ...then print request sent and response returned
        .andExpect( status().isOk() ) // expect 200 status code
        .andExpect( jsonPath("$.length()").value(books.size() ) )
        .andExpect( jsonPath("$[0].id").value( books.get(0).getId() ) )
		.andExpect( jsonPath("$[0].author").value( books.get(0).getAuthor() ) )
		.andExpect( jsonPath("$[0].size").value( books.get(0).getSize() ) )
		.andExpect( jsonPath("$[1].id").value( books.get(1).getId() ) )
		.andExpect( jsonPath("$[1].author").value( books.get(1).getAuthor() ) )
		.andExpect( jsonPath("$[1].size").value( books.get(1).getSize() ) );
	
	}
	
	@Test
	void testGetUsers() throws Exception {
		String uri = STARTING_URI + "/users";
		List<User> users = Arrays.asList(
				new User("User1", "Password"),
				new User("user2", "Password"));
		when(serv.getAllUsers()).thenReturn(users);
		mvc.perform( get(uri) ) // perform request...
        .andDo( print() )   // ...then print request sent and response returned
        .andExpect( status().isOk() )
        .andExpect( jsonPath("$.length()").value(users.size() ) )
        .andExpect( jsonPath("$[0].username").value(users.get(0).getUsername()))
        .andExpect( jsonPath("$[1].username").value(users.get(1).getUsername()));
		
	}
	
//	@Test
//	void testGetSalesByUser() throws Exception {
//		String uri = STARTING_URI + "/sales/user/User1";
//		User user1 = new User("User1", "Password");
//		Book book1 = new Book("Book1", "Author1", 2.2, 120, 5);
//		Book book2 = new Book("Book2", "Author2", 2.2, 100, 5);
//		List<Sale> sales = Arrays.asList(
//				new Sale(book1, user1, 1),
//				new Sale(book2, user1, 1));
//		when(serv.getAllSales()).thenReturn(sales);
//				
//	}
}