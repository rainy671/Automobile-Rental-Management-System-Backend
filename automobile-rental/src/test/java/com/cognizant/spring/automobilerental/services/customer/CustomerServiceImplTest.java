package com.cognizant.spring.automobilerental.services.customer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import com.cognizant.spring.automobilerental.dto.BookACarDto;
import com.cognizant.spring.automobilerental.dto.CarDto;
import com.cognizant.spring.automobilerental.dto.CarDtoListDto;
import com.cognizant.spring.automobilerental.dto.SearchCarDto;
import com.cognizant.spring.automobilerental.entity.BookACar;
import com.cognizant.spring.automobilerental.entity.Car;
import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.repository.BookACarRepository;
import com.cognizant.spring.automobilerental.repository.CarRepository;
import com.cognizant.spring.automobilerental.repository.UserRepository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CustomerServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookACarRepository bookACarRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testGetAllCars() {
        // Arrange
        Car car = new Car();
        car.setName("TESLA-ABC");
        car.setBrand("TESLA");
        car.setColor("BLACK");
        car.setPrice(500L);
        car.setYear(new Date());
        car.setType("Electric");
        car.setDescription("TESLA-ABC-Electric");
        car.setTransmission("Automatic");
        List<Car> carList = Arrays.asList(car);
        
        when(carRepository.findAll()).thenReturn(carList);

        // Act
        List<CarDto> result = customerService.getAllCars();

        // Assert
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testBookACar() {
        // Arrange
    	BookACarDto bookACarDto = new BookACarDto();
        bookACarDto.setCarId(1L);
        bookACarDto.setUserId(1L);
        bookACarDto.setFromDate(new Date());
        bookACarDto.setToDate(new Date());
        Car car = new Car();
        car.setPrice(10000L);
        User user = new User();
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookACarRepository.save(any(BookACar.class))).thenReturn(new BookACar());

        // Act
        boolean result = customerService.bookACar(bookACarDto);

        // Assert
        assertTrue(result);
        verify(carRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(bookACarRepository, times(1)).save(any(BookACar.class));
    }
    

    @Test
    public void testGetCarById() {
        // Arrange
        Car car = new Car();
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        // Act
        CarDto result = customerService.getCarById(1L);

        // Assert
        assertNotNull(result);
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetBookingsByUserId() {
        // Arrange
        BookACar bookACar = new BookACar();
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setName("Test User");
        user.setId(1L);
        Car car = new Car();
        car.setId(1L);
        bookACar.setUser(user);
        bookACar.setCar(car);
        List<BookACar> bookACarList = Arrays.asList(bookACar);
        when(bookACarRepository.findAllByUserId(1L)).thenReturn(bookACarList);

        // Act
        List<BookACarDto> result = customerService.getBookingsByUserId(1L);

        // Assert
        assertEquals(1, result.size());
//        verify(bookACarRepository, times(1)).findAllByUserId(1L);
    }

    @Test
    public void testSearchCar() {
        // Arrange
        SearchCarDto searchCarDto = new SearchCarDto();
        searchCarDto.setBrand("Test Brand");
        searchCarDto.setType("Sedan");
        searchCarDto.setTransmission("Automatic");
        searchCarDto.setColor("Red");

        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());

        List<Car> carList = Arrays.asList(car);
        when(carRepository.findAll(any(Example.class))).thenReturn(carList);

        // Act
        CarDtoListDto result = customerService.searchCar(searchCarDto);

        // Assert
        assertEquals(1, result.getCarDtoList().size());
        verify(carRepository, times(1)).findAll(any(Example.class));
    }
}

