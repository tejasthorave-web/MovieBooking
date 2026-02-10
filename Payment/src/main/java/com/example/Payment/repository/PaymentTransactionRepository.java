package com.example.Payment.repository;

import com.example.Payment.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {


    Optional<PaymentTransaction> findByIdempotencyKey(String idempotencyKey);


    Optional<PaymentTransaction> findByBookingId(String bookingId);
}
