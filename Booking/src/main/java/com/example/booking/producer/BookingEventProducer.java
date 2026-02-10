//package com.example.booking.producer;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class BookingEventProducer {
//    @Autowired
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public void publishBookingEvent(String event) {
//        kafkaTemplate.send("booking-events", event);
//    }
//}
