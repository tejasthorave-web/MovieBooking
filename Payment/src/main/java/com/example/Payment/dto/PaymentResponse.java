package com.example.Payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PaymentResponse {
    private String orderId;
    private String status;
}
