package com.example.Payment.controller;


import com.example.Payment.entity.PaymentTransaction;
import com.example.Payment.service.PaymentIdempotentService;
import com.example.Payment.dto.BookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentRetryController {


    private final PaymentIdempotentService service;


    @PostMapping("/order")
    public ResponseEntity<?> createOrder(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestBody BookingRequest request) {


        PaymentTransaction txn = service.createOrderSafely(
                request.getBookingId(),
                request.getAmount(),
                idempotencyKey
        );


        return ResponseEntity.ok(txn);
    }
}