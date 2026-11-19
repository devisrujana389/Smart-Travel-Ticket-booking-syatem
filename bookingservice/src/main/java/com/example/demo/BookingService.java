package com.example.demo;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.booking.client.BookingClient;
import com.example.demo.booking.client.CustomerClient;
import com.example.demo.booking.client.CustomerResponse;
import com.example.demo.booking.client.HotelClient;
import com.example.demo.booking.client.TrainClient;
import com.example.demo.dto.BusResponse;
import com.example.demo.dto.HotelResponse;
import com.example.demo.dto.TrainResponse;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingClient bookingClient;
    private final TrainClient trainClient;
    private final HotelClient hotelClient;
    private final PaymentService paymentservice;
    private final CustomerClient customerClient;

    public BookingService(BookingRepository bookingRepository,
            BookingClient bookingClient,
            TrainClient trainClient,
            HotelClient hotelClient,
            PaymentService paymentservice,
            CustomerClient customerClient) {

        this.bookingRepository = bookingRepository;
        this.bookingClient = bookingClient;
        this.trainClient = trainClient;
        this.hotelClient = hotelClient;
        this.paymentservice = paymentservice;
        this.customerClient = customerClient;
    }
    // CREATE
    public Booking createBooking(Booking booking) {
    	 CustomerResponse customer =
    	            customerClient.getCustomerById(booking.getCustomerId());

        if (booking.getBookingType().equalsIgnoreCase("BUS")) {

            BusResponse bus = bookingClient.getBusById(booking.getServiceId());

            booking.setAmount(bus.getTicketPrice());
        }

        else if (booking.getBookingType().equalsIgnoreCase("TRAIN")) {

            TrainResponse train = trainClient.getTrainById(booking.getServiceId());

            booking.setAmount(train.getTicketPrice());
        }

        else if (booking.getBookingType().equalsIgnoreCase("HOTEL")) {

            HotelResponse hotel = hotelClient.getHotelById(booking.getServiceId());

            booking.setAmount(hotel.getPricePerNight());
        }

        booking.setBookingDate(java.time.LocalDateTime.now());

        String paymentResult = paymentservice.makepayment(booking.getAmount());

        if (paymentResult.equals("Payment successful")) {
            booking.setStatus("CONFIRMED");
        } else {
            booking.setStatus("PAYMENT_FAILED");
        }

        return bookingRepository.save(booking);
    }

    // GET ALL
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }

    // GET BY ID
    public Booking getBookingById(int bookingId) {

        return bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with ID: " + bookingId
                        ));
    }

    // UPDATE
    public Booking updateBooking(int bookingId, Booking updatedBooking) {

        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with ID: " + bookingId
                        ));

        existingBooking.setCustomerId(updatedBooking.getCustomerId());
        existingBooking.setBookingType(updatedBooking.getBookingType());
        existingBooking.setServiceId(updatedBooking.getServiceId());
        existingBooking.setTravelDate(updatedBooking.getTravelDate());
        existingBooking.setBookingDate(updatedBooking.getBookingDate());
        existingBooking.setSource(updatedBooking.getSource());
        existingBooking.setDestination(updatedBooking.getDestination());
        existingBooking.setAmount(updatedBooking.getAmount());
        existingBooking.setStatus(updatedBooking.getStatus());

        return bookingRepository.save(existingBooking);
    }

    // DELETE
    public void deleteBooking(int bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with ID: " + bookingId
                        ));

        bookingRepository.delete(booking);
    }
  

    public List<Booking> getBookingsByCustomerId(int customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
    
    public Booking cancelBooking(int bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with ID: " + bookingId
                        ));

        booking.setStatus("CANCELLED");

        return bookingRepository.save(booking);
    }
}