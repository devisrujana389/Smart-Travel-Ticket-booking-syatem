package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;


    // ADD HOTEL
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelEntity addHotel(@RequestBody HotelEntity hotel) {
        return hotelService.addHotel(hotel);
    }


    // FETCH ALL HOTELS
    @GetMapping("/fetch")
    public List<HotelEntity> getAllHotels() {
        return hotelService.getAllHotels();
    }


    // FETCH HOTEL BY ID
    @GetMapping("/fetch/{hotelId}")
    public HotelEntity getHotelById(@PathVariable int hotelId) {
        return hotelService.getHotelById(hotelId);
    }


    // UPDATE HOTEL
    @PutMapping("/update/{hotelId}")
    public HotelEntity updateHotel(
            @PathVariable int hotelId,
            @RequestBody HotelEntity hotelDetails) {

        return hotelService.updateHotel(hotelId, hotelDetails);
    }


    // DELETE HOTEL
    @DeleteMapping("/delete/{hotelId}")
    public String deleteHotel(@PathVariable int hotelId) {
        return hotelService.deleteHotel(hotelId);
    }


    // SEARCH HOTEL BY LOCATION
    @GetMapping("/search")
    public List<HotelEntity> searchHotel(
            @RequestParam String location) {

        return hotelService.searchByLocation(location);
    }
 // RESERVE ROOMS
    @PutMapping("/reserve/{hotelId}")
    public HotelEntity reserveRooms(
            @PathVariable int hotelId,
            @RequestParam int rooms) {

        return hotelService.reserveRooms(hotelId, rooms);
    }
}