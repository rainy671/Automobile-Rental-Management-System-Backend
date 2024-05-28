package com.cognizant.spring.automobilerental.repository;

import java.util.List;
	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.spring.automobilerental.entity.BookACar;


@Repository
public interface BookACarRepository extends JpaRepository<BookACar,Long>{

	List<BookACar> findAllByUserId(Long userId);
		// TODO Auto-generated method stub

}
