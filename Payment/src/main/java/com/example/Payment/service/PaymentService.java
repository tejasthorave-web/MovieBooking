package com.example.Payment.service;


import com.example.Payment.dto.CreateOrderRequest;
import com.example.Payment.dto.PaymentResponse;
import com.example.Payment.dto.PaymentVerificationRequest;
import com.example.Payment.entity.Payment;
import com.example.Payment.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;


    private final String KEY = "rzp_test_key";
    private final String SECRET = "rzp_secret";


    public PaymentResponse createOrder(CreateOrderRequest request) throws Exception {


        RazorpayClient client = new RazorpayClient(KEY, SECRET);


        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", request.getAmount() * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "booking_" + request.getBookingId());


        Order order = client.orders.create(orderRequest);


        Payment payment = Payment.builder()
                .bookingId(request.getBookingId())
                .razorpayOrderId(order.get("id"))
                .amount(request.getAmount())
                .status("CREATED")
                .build();


        paymentRepository.save(payment);


        return new PaymentResponse(order.get("id"), "CREATED");
    }


    public void verifyPayment(PaymentVerificationRequest request) {


        Payment payment = paymentRepository.findAll()
                .stream()
                .filter(p -> p.getRazorpayOrderId().equals(request.getRazorpayOrderId()))
                .findFirst()
                .orElseThrow();


        payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
        payment.setStatus("SUCCESS");


        paymentRepository.save(payment);


// call booking-service to confirm booking
    }
}