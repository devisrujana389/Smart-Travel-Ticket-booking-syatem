package com.example.demo.booking.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BusResponse;
import com.example.demo.dto.HotelResponse;
import com.example.demo.dto.TrainResponse;

@RestController
@RequestMapping("/booking")
public class BookingClientController {

    private final BookingClient bookingclient;
    @Autowired
    private TrainClient trainClient;
    @Autowired
    private HotelClient hotelclient;

    public BookingClientController(BookingClient bookingclient) {
        this.bookingclient = bookingclient;
    }

    @GetMapping("/bus/{id}")
    public BusResponse getBus(@PathVariable int id) {
        return bookingclient.getBusById(id);
    }
    @PutMapping("/bus/reserve/{id}")
    public BusResponse reserveBus(
            @PathVariable int id,
            @RequestParam int seats) {

        return bookingclient.reserveSeats(id, seats);
    }
    
    @GetMapping("/train/{id}")
    public TrainResponse getTrain(@PathVariable int id) {
        return trainClient.getTrainById(id);
    }
    
    @PutMapping("/train/reserve/{id}")
    public TrainResponse reserveSeats(
            @PathVariable int id,
            @RequestParam int seats) {
    	  return trainClient.reserveTrainSeats(id, seats);
    }
    
    @GetMapping("/hotel/{id}")
    public HotelResponse getHotel(@PathVariable int id) {
        return hotelclient.getHotelById(id);
    }

    @PutMapping("/hotel/reserve/{id}")
    public HotelResponse reserveHotel(
            @PathVariable int id,
            @RequestParam int rooms) {

        return hotelclient.reserveHotel(id, rooms);
    }

}