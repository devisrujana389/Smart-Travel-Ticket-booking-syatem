package com.example.demo.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.BusResponse;
@FeignClient(name="BUS-SERVICE")
public interface BookingClient {
	
	@GetMapping("bus/fetch/{id}")
    BusResponse getBusById(@PathVariable("id") int id);
	
	@PutMapping("/bus/reserve/{id}")
	BusResponse reserveSeats(
	        @PathVariable("id") int id,
	        @RequestParam("seats") int seats
	);

}