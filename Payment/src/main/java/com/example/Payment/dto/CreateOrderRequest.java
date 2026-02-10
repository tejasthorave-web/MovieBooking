package com.example.Payment.dto;

import lombok.Data;


@Data
public class CreateOrderRequest {
    private Long bookingId;
    private Double amount;
}
