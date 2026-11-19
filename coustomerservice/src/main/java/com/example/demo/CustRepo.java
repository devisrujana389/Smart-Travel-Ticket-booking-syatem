package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustRepo extends JpaRepository<Customer, Integer>{
	  Optional<Customer> findByuserName(String username);
}
	


