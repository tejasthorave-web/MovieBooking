package com.example.Payment.entity;

import com.example.Payment.ENUM.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "payment_transactions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idempotencyKey"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String bookingId;


    @Column(nullable = false)
    private String idempotencyKey;


    private String razorpayOrderId;
    private String razorpayPaymentId;


    private Double amount;


    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // CREATED, SUCCESS, FAILED


    private String requestHash;
}