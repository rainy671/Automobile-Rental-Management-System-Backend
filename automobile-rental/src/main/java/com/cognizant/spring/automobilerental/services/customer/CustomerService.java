package com.cognizant.spring.automobilerental.services.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.spring.automobilerental.dto.BookACarDto;
import com.cognizant.spring.automobilerental.dto.CarDto;
import com.cognizant.spring.automobilerental.dto.CarDtoListDto;
import com.cognizant.spring.automobilerental.dto.SearchCarDto;

@Service
public interface CustomerService {
	
	List<CarDto> getAllCars();
	boolean bookACar(BookACarDto bookACarDto);
	CarDto getCarById(Long carId);
	List<BookACarDto> getBookingsByUserId(Long userId);
	CarDtoListDto searchCar(SearchCarDto searchCarDto);

}
