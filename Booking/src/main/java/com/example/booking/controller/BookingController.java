package com.example.booking.controller;

import com.example.booking.dto.BookingRequest;
import com.example.booking.entity.Booking;
import com.example.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/lock")
    public ResponseEntity<String> lockSeats(
            @RequestParam Long showId,
            @RequestParam Long userId,
            @RequestBody List<Long> seatIds) {

        return ResponseEntity.ok(bookingService.lockSeats(showId, seatIds, userId));
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(
                request.getUserId(),
                request.getShowId(),
                request.getSeatIds(),
                request.getAmount());
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getBooking(id);
    }
}

