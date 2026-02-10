package com.example.Payment.dto;

import lombok.Data;


@Data
public class PaymentVerificationRequest {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String signature;
}