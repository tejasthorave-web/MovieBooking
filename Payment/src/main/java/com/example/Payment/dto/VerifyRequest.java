package com.example.Payment.dto;

import lombok.Data;

@Data
public  class VerifyRequest {
    private String orderId;
    private String paymentId;
    private String signature;

}
