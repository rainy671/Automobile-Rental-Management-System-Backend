//package com.cognizant.spring.automobilerental.repository;
//
//import com.cognizant.spring.automobilerental.entity.BookACar;
//import com.cognizant.spring.automobilerental.entity.User;
//import com.cognizant.spring.automobilerental.enums.BookCarStatus;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class BookACarRepositoryTest {
//
//    @Mock
//    private BookACarRepository bookACarRepository;
//
//    @Mock
//    private BookACarRepository mockBookACarRepository;
//
//    @Test
//    public void testFindAllByUserId() {
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//
//        BookACar bookACar = new BookACar();
//        bookACar.setFromDate(new Date());
//        bookACar.setToDate(new Date());
//        bookACar.setDays(5L);
//        bookACar.setPrice(500L);
//        bookACar.setBookCarStatus(BookCarStatus.APPROVED);
//        bookACar.setUser(user);
//
//        when(mockBookACarRepository.findAllByUserId(anyLong())).thenReturn(Arrays.asList(bookACar));
//
//        List<BookACar> foundBookings = bookACarRepository.findAllByUserId(user.getId());
//
//        assertEquals(1, foundBookings.size());
//        assertEquals(user.getEmail(), foundBookings.get(0).getUser().getEmail());
//    }
//}
//
//package com.cognizant.spring.automobilerental.repository;
//
//import com.cognizant.spring.automobilerental.entity.BookACar;
//import com.cognizant.spring.automobilerental.entity.User;
//import com.cognizant.spring.automobilerental.enums.BookCarStatus;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class BookACarRepositoryTest {
//
//    @Autowired
//    private BookACarRepository bookACarRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testFindAllByUserId() {
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//        userRepository.save(user);
//
//        BookACar bookACar = new BookACar();
//        bookACar.setFromDate(new Date());
//        bookACar.setToDate(new Date());
//        bookACar.setDays(5L);
//        bookACar.setPrice(500L);
//        bookACar.setBookCarStatus(BookCarStatus.APPROVED);
//        bookACar.setUser(user);
//        bookACarRepository.save(bookACar);
//
//        List<BookACar> foundBookings = bookACarRepository.findAllByUserId(user.getId());
//
//        assertEquals(1, foundBookings.size());
//        assertEquals(user.getEmail(), foundBookings.get(0).getUser().getEmail());
//    }
//}

