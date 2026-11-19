package com.example.demo.booking.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.HotelResponse;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {

    @GetMapping("/hotel/fetch/{id}")
    HotelResponse getHotelById(@PathVariable("id") int id);

    @PutMapping("/hotel/reserve/{id}")
    HotelResponse reserveHotel(
            @PathVariable("id") int id,
            @RequestParam("rooms") int rooms
    );
}