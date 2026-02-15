package com.example.booking.service;

import com.example.booking.entity.Booking;
import com.example.booking.entity.BookingSeat;
import com.example.booking.entity.BookingStatus;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.BookingSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingSeatRepository bookingSeatRepository;

    @Autowired
    private SeatLockService seatLockService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public String lockSeats(Long showId, List<Long> seatIds, Long userId) {

        boolean locked = seatLockService.lockSeats(showId, seatIds, userId);
        if (!locked) {
            return "Seats already locked!";
        }

        return "Seats locked successfully";
    }

    public Booking createBooking(Long userId, Long showId, List<Long> seatIds, Double amount) {

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setShowId(showId);
        booking.setTotalAmount(amount);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCreatedAt(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        for (Long seatId : seatIds) {
            BookingSeat bs = new BookingSeat();
            bs.setBookingId(savedBooking.getId());
            bs.setSeatId(seatId);
            bookingSeatRepository.save(bs);
        }

        kafkaTemplate.send("booking-created-topic", savedBooking);

        return savedBooking;
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}
