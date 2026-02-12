package com.example.Theatre.service;


import com.example.Theatre.entity.Screen;
import com.example.Theatre.entity.Seat;
import com.example.Theatre.repository.ScreenRepository;
import com.example.Theatre.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SeatService {


    private final SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }
}
