package com.example.Payment.controller;


import com.example.Payment.dto.PaymentRequest;
import com.example.Payment.dto.VerifyRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {


    @Value("${razorpay.key}")
    private String key;


    @Value("${razorpay.secret}")
    private String secret;


    // 1. Create Order
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody PaymentRequest request) {
        try {
            RazorpayClient client = new RazorpayClient(key, secret);


            JSONObject options = new JSONObject();
            options.put("amount", request.getAmount() * 100); // amount in paise
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());


            Order order = client.orders.create(options);
            return ResponseEntity.ok(order.toJson());


        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    // 2. Verify Payment Signature
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody VerifyRequest request) {
        try {
            String payload = request.getOrderId() + "|" + request.getPaymentId();
            Utils.verifySignature(payload, request.getSignature(), secret);


// TODO: update booking status SUCCESS
            return ResponseEntity.ok("Payment verified successfully");


        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid signature");
        }
    }
}