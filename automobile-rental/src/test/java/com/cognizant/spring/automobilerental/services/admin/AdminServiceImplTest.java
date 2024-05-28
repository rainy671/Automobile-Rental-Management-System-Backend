package com.cognizant.spring.automobilerental.services.admin;

import com.cognizant.spring.automobilerental.dto.BookACarDto;
import com.cognizant.spring.automobilerental.dto.CarDto;
import com.cognizant.spring.automobilerental.dto.CarDtoListDto;
import com.cognizant.spring.automobilerental.dto.SearchCarDto;
import com.cognizant.spring.automobilerental.entity.BookACar;
import com.cognizant.spring.automobilerental.entity.Car;
import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.enums.BookCarStatus;
import com.cognizant.spring.automobilerental.repository.BookACarRepository;
import com.cognizant.spring.automobilerental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private BookACarRepository bookACarRepository;
    
//    @InjectMocks
//    private CarDto cardto;
    
    
    @Test
    public void testPostCar()throws IOException {
    	CarDto carDto= new CarDto();
    	carDto.setName("TESLA-ABC");
    	carDto.setBrand("TESLA");
    	carDto.setColor("BLACK");
    	carDto.setPrice(500L);
    	carDto.setYear(new Date());
    	carDto.setType("Electric");
    	carDto.setDescription("TESLA-ABC-Electric");
    	carDto.setTransmission("Automatic");
    	carDto.setImage(new MockMultipartFile("testimage", "Hello, World!".getBytes()));
        
        Car car = new Car();
        car.setName(carDto.getName());
        car.setBrand(carDto.getBrand());
        car.setColor(carDto.getColor());
        car.setPrice(carDto.getPrice());
        car.setYear(carDto.getYear());
        car.setType(carDto.getType());
        car.setDescription(carDto.getDescription());
        car.setTransmission(carDto.getTransmission());
        car.setImage(carDto.getImage().getBytes());

//        when(carRepository.save(any(Car.class))).thenReturn(car);

        // Act
//        boolean result = carService.postCar(carDto);
//
//        // Assert
//        assertTrue(result);
//        verify(carRepository, times(1)).save(any(Car.class));
        
        when(carRepository.save(car)).thenReturn(car);
        assertEquals(true,adminService.postCar(carDto));
    }

    @Test
    public void testGetAllCars() {
        Car car = new Car();
        car.setName("TESLA-ABC");
        car.setBrand("TESLA");
        car.setColor("BLACK");
        car.setPrice(500L);
        car.setYear(new Date());
        car.setType("Electric");
        car.setDescription("TESLA-ABC-Electric");
        car.setTransmission("Automatic");

        when(carRepository.findAll()).thenReturn(Arrays.asList(car));

        List<CarDto> foundCars = adminService.getAllCars();

        assertEquals(1, foundCars.size());
        assertEquals("TESLA-ABC", foundCars.get(0).getName());
    }

    public void deleteCarTest() {
    	Long id=1L;
    	doNothing().when(carRepository).deleteById(id);
    	adminService.deleteCar(id);

        // Assert
        verify(carRepository, times(1)).deleteById(id);
    }

    public void updateCarTest()throws IOException{
    	CarDto carDto= new CarDto();
    	carDto.setName("TESLA-ABC");
    	carDto.setBrand("TESLA");
    	carDto.setColor("BLACK");
    	carDto.setPrice(500L);
    	carDto.setYear(new Date());
    	carDto.setType("Electric");
    	carDto.setDescription("TESLA-ABC-Electric");
    	carDto.setTransmission("Automatic");
    	carDto.setImage(new MockMultipartFile("testimage", "Hello, World!".getBytes()));
        
        Car car = new Car();
        car.setName(carDto.getName());
        car.setBrand(carDto.getBrand());
        car.setColor(carDto.getColor());
        car.setPrice(carDto.getPrice());
        car.setYear(carDto.getYear());
        car.setType(carDto.getType());
        car.setDescription(carDto.getDescription());
        car.setTransmission(carDto.getTransmission());
        car.setImage(carDto.getImage().getBytes());
        
        when(carRepository.save(car)).thenReturn(car);
        assertTrue(adminService.updateCar(carDto.getId(),carDto));
    }
//    public List<BookACarDto> getBookings() {
//        return bookACarRepository.findAll().stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
//    }
 

    	    @Test
    	    public void testGetBookings() {
    	        // Arrange
    	        BookACar bookACar = new BookACar();
    	        bookACar.setId(1L);
    	        bookACar.setFromDate(new Date());
    	        bookACar.setToDate(new Date());
    	        bookACar.setDays(5L);
    	        bookACar.setPrice(10000L);
    	        bookACar.setBookCarStatus(BookCarStatus.APPROVED);
    	        User user = new User();
    	        user.setEmail("testuser@example.com");
    	        user.setName("Test User");
    	        user.setId(1L);
    	        Car car = new Car();
    	        car.setId(1L);
    	        bookACar.setUser(user);
    	        bookACar.setCar(car);
    	      
    	        List<BookACar> bookACarList = Arrays.asList(bookACar);
    	        when(bookACarRepository.findAll()).thenReturn(bookACarList);

    	        // Act
    	        List<BookACarDto> result = adminService.getBookings();

    	        // Assert
    	        assertEquals(1, result.size());
    	        assertEquals(1L, result.get(0).getId());
    	    }
    	    

    	        @Test
    	        public void testChangeBookingStatus() {
    	            
    	            Long bookingId = 1L;
    	            String status = "Approve";
    	            BookACar bookACar = new BookACar();
    	            bookACar.setId(bookingId);
    	            bookACar.setBookCarStatus(BookCarStatus.PENDING);
    	            when(bookACarRepository.findById(bookingId)).thenReturn(Optional.of(bookACar));
    	            when(bookACarRepository.save(any(BookACar.class))).thenReturn(bookACar);

    	            
    	            boolean result = adminService.changeBookingStatus(bookingId, status);

    	            
    	            assertTrue(result);
    	            assertEquals(BookCarStatus.APPROVED, bookACar.getBookCarStatus());
//    	            verify(bookACarRepository, times(1)).findById(bookingId);
//    	            verify(bookACarRepository, times(1)).save(any(BookACar.class));
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
    	            CarDtoListDto result = adminService.searchCar(searchCarDto);

    	            // Assert
    	            assertEquals(1, result.getCarDtoList().size());
//    	            verify(carRepository, times(1)).findAll(any(Example.class));
    	        }
    	    }

    	

    	
//    	when(bookACarRepository.findAll()).thenReturn(Arrays.asList(bookACarDto))
    


