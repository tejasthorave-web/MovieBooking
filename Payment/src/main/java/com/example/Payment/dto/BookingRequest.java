package com.example.Payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {

    private Long userId;
    private Long showId;
    private String bookingId;
    private Double amount;
    private List<String> seatNumbers;

}