package com.cognizant.spring.automobilerental.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.spring.automobilerental.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{

	
}
