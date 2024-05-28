package com.cognizant.spring.automobilerental.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.spring.automobilerental.dto.BookACarDto;
import com.cognizant.spring.automobilerental.dto.CarDto;
import com.cognizant.spring.automobilerental.dto.CarDtoListDto;
import com.cognizant.spring.automobilerental.dto.SearchCarDto;
import com.cognizant.spring.automobilerental.services.customer.CustomerService;

@SpringBootTest
public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@Test
	public void testGetAllCars() {
		
		when(customerService.getAllCars()).thenReturn(Arrays.asList(new CarDto()));

		
		ResponseEntity<List<CarDto>> result = customerController.getAllCars();

		
		assertEquals(HttpStatus.OK, result.getStatusCode());
		
	}

	@Test
	public void testBookACar() {
		
		BookACarDto bookACarDto = new BookACarDto();
		when(customerService.bookACar(bookACarDto)).thenReturn(true);

		
		ResponseEntity<Void> result = customerController.bookACar(bookACarDto);

		
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		
	}

	@Test
	public void testGetCarById() {
		
		Long id = 1L;
		when(customerService.getCarById(id)).thenReturn(new CarDto());

		
		ResponseEntity<CarDto> result = customerController.getCarById(id);

		
		assertEquals(HttpStatus.OK, result.getStatusCode());
		
	}

	@Test
	public void testGetBookingsByUserId() {
		
		Long id = 1L;
		when(customerService.getBookingsByUserId(id)).thenReturn(Arrays.asList(new BookACarDto()));

		
		ResponseEntity<List<BookACarDto>> result = customerController.getBookingsByUserId(id);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		
	}

	 @Test
	    public void testSearchCar() {
	        
	        SearchCarDto searchCarDto = new SearchCarDto();
	        when(customerService.searchCar(searchCarDto)).thenReturn(new CarDtoListDto());

	       
	        ResponseEntity<?> result = customerController.searchCar(searchCarDto);

	        
	        assertEquals(HttpStatus.OK, result.getStatusCode());
	        
	    }
}
