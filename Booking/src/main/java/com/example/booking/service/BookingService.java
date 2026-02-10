package com.example.booking.service;

import com.example.booking.dto.BookingRequest;
import com.example.booking.dto.BookingResponse;
import com.example.booking.entity.Booking;
//import com.example.booking.producer.BookingEventProducer;
import com.example.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RedisTemplate<String, Object> redisTemplate;
//    private final BookingEventProducer producer;

    public BookingResponse bookTicket(BookingRequest request) {

        String lockKey = "seat_lock_" + request.getShowId() + "_" + request.getSeatNumbers();

        Boolean isLocked = redisTemplate.hasKey(lockKey);
        if (Boolean.TRUE.equals(isLocked)) {
            return BookingResponse.builder()
                    .status("FAILED")
                    .message("Seat already locked!")
                    .build();
        }

        redisTemplate.opsForValue().set(lockKey, "LOCKED");

        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .showId(request.getShowId())
                .seatNumbers(String.valueOf(request.getSeatNumbers()))
                .status("CONFIRMED")
                .bookingTime(LocalDateTime.now())
                .amount(250.0)
                .build();

        Booking saved = bookingRepository.save(booking);

//        producer.publishBookingEvent("Booking Confirmed: " + saved.getId());

        return BookingResponse.builder()
                .bookingId(saved.getId())
                .status("SUCCESS")
                .message("Booking confirmed")
                .build();
    }
}


