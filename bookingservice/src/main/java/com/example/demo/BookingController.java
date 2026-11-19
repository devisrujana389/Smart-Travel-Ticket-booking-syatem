package com.example.demo;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // CREATE
    @PostMapping("/add")
    public Booking createBooking(@RequestBody Booking booking) {

        return bookingService.createBooking(booking);
    }

    // GET ALL
    @GetMapping("/fetch")
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }

    // GET BY ID
    @GetMapping("/fetch/{bookingId}")
    public Booking getBookingById(
            @PathVariable int bookingId) {

        return bookingService.getBookingById(bookingId);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<Booking> getBookingsByCustomerId(
            @PathVariable int customerId) {

        return bookingService.getBookingsByCustomerId(customerId);
    }

    // UPDATE
    @PutMapping("/update/{bookingId}")
    public Booking updateBooking(
            @PathVariable int bookingId,
            @RequestBody Booking booking) {

        return bookingService.updateBooking(bookingId, booking);
    }

    // DELETE
    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable int bookingId) {

        bookingService.deleteBooking(bookingId);

        return ResponseEntity.ok(
                "Booking deleted successfully"
        );
    }
    
    @PutMapping("/cancel/{bookingId}")
    public Booking cancelBooking(@PathVariable int bookingId) {

        return bookingService.cancelBooking(bookingId);
    }
}
