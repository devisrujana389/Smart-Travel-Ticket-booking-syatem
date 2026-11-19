package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundEx;

@Service
public class HotelService {

    @Autowired
    private HotelRepo hotelRepo;


    // ADD HOTEL
    public HotelEntity addHotel(HotelEntity hotel) {
        return hotelRepo.save(hotel);
    }


    // FETCH ALL HOTELS
    public List<HotelEntity> getAllHotels() {
        return hotelRepo.findAll();
    }


    // FETCH HOTEL BY ID
    public HotelEntity getHotelById(int hotelId) {

        return hotelRepo.findById(hotelId)
                .orElseThrow(() ->
                    new ResourceNotFoundEx(
                        "Hotel not found with id: " + hotelId
                    )
                );
    }


    // UPDATE HOTEL
    public HotelEntity updateHotel(int hotelId, HotelEntity hotelDetails) {

        HotelEntity existingHotel =
                hotelRepo.findById(hotelId)
                .orElseThrow(() ->
                    new ResourceNotFoundEx(
                        "Hotel not found with id: " + hotelId
                    )
                );

        existingHotel.setHotelName(hotelDetails.getHotelName());
        existingHotel.setLocation(hotelDetails.getLocation());
        existingHotel.setAddress(hotelDetails.getAddress());
        existingHotel.setRoomType(hotelDetails.getRoomType());
        existingHotel.setPricePerNight(hotelDetails.getPricePerNight());
        existingHotel.setAvailableRooms(hotelDetails.getAvailableRooms());
        existingHotel.setRating(hotelDetails.getRating());

        return hotelRepo.save(existingHotel);
    }


    // DELETE HOTEL
    public String deleteHotel(int hotelId) {

        HotelEntity hotel =
                hotelRepo.findById(hotelId)
                .orElseThrow(() ->
                    new ResourceNotFoundEx(
                        "Hotel not found with id: " + hotelId
                    )
                );

        hotelRepo.delete(hotel);

        return "Hotel deleted successfully with id: " + hotelId;
    }


    // SEARCH HOTEL BY LOCATION
    public List<HotelEntity> searchByLocation(String location) {

        return hotelRepo.findByLocation(location);
    }
    public HotelEntity reserveRooms(int hotelId, int rooms) {

        HotelEntity hotel = hotelRepo.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        if (hotel.getAvailableRooms() < rooms) {
            throw new RuntimeException("Not enough rooms available");
        }

        hotel.setAvailableRooms(
                hotel.getAvailableRooms() - rooms
        );

        return hotelRepo.save(hotel);
    }
}