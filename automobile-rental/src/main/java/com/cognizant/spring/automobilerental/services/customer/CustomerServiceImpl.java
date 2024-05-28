package com.cognizant.spring.automobilerental.services.customer;

import java.util.List;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.enums.BookCarStatus;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import com.cognizant.spring.automobilerental.dto.CarDto;
import com.cognizant.spring.automobilerental.dto.CarDtoListDto;
import com.cognizant.spring.automobilerental.dto.SearchCarDto;
import com.cognizant.spring.automobilerental.entity.BookACar;
import com.cognizant.spring.automobilerental.entity.Car;
import com.cognizant.spring.automobilerental.repository.BookACarRepository;
import com.cognizant.spring.automobilerental.repository.CarRepository;
import com.cognizant.spring.automobilerental.repository.UserRepository;

import com.cognizant.spring.automobilerental.dto.BookACarDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	private final CarRepository carRepository;
	private final UserRepository userRepository;
	private final BookACarRepository bookACarRepository;
	
	 @Override
	    public List<CarDto> getAllCars() {

	        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
	    }

	
@Override
public boolean bookACar(BookACarDto bookACarDto) {
    Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
    Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
    if (optionalCar.isPresent() && optionalUser.isPresent()){
        Car existingCar = optionalCar.get();
        BookACar bookACar = new BookACar();
        bookACar.setUser(optionalUser.get());
        bookACar.setCar(existingCar);
        bookACar.setBookCarStatus(BookCarStatus.PENDING);
        long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
        long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds); // Use MILLISECONDS instead of MICROSECONDS
        bookACar.setDays(days);
        bookACar.setFromDate(bookACarDto.getFromDate()); // Set fromDate from bookACarDto
        bookACar.setToDate(bookACarDto.getToDate());     // Set toDate from bookACarDto
        bookACar.setPrice(existingCar.getPrice() * days);
        bookACarRepository.save(bookACar);
        return true;
    }

    return false;
}
		@Override
		public CarDto getCarById(Long carId) {
		    Optional<Car> optionalCar =carRepository.findById(carId);
		    return optionalCar.map(Car::getCarDto).orElse(null);
		}
		@Override
	    public List<BookACarDto> getBookingsByUserId(Long userId) {
	        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
	    }


		@Override
		public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
			Car car = new Car();
	        car.setBrand(searchCarDto.getBrand());
	        car.setType(searchCarDto.getType());
	        car.setTransmission(searchCarDto.getTransmission());
	        car.setColor(searchCarDto.getColor());
	        ExampleMatcher exampleMatcher =
	                ExampleMatcher.matchingAll()
	                        .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
	                        .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
	                        .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
	                        .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
	        Example<Car> carExample = Example.of(car, exampleMatcher);
	        List<Car> carList = carRepository.findAll(carExample);
	        CarDtoListDto carDtoListDto = new CarDtoListDto();
	        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
	        return carDtoListDto;
		}
		
	
}



