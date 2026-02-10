package com.example.Payment.service;


import com.example.Payment.ENUM.PaymentStatus;
import com.example.Payment.entity.PaymentTransaction;
import com.example.Payment.repository.PaymentTransactionRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PaymentIdempotentService {


    private final PaymentTransactionRepository repository;
    private final RazorpayClient razorpayClient;


    public PaymentTransaction createOrderSafely(String bookingId, Double amount, String idempotencyKey) {


// STEP 1: Check existing transaction
        Optional<PaymentTransaction> existingTxn = repository.findByIdempotencyKey(idempotencyKey);


        if (existingTxn.isPresent()) {
            return existingTxn.get(); // Safe retry: return existing order
        }


// STEP 2: Create Razorpay Order
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100);
        options.put("currency", "INR");
        options.put("receipt", bookingId);


        Order order;
        try {
            order = razorpayClient.orders.create(options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }


// STEP 3: Persist transaction
        PaymentTransaction txn = PaymentTransaction.builder()
                .bookingId(bookingId)
                .amount(amount)
                .idempotencyKey(idempotencyKey)
                .razorpayOrderId(order.get("id"))
                .status(PaymentStatus.CREATED)
                .build();


        return repository.save(txn);
    }
}