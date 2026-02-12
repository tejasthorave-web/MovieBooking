package com.example.Theatre.service;



import com.example.Theatre.entity.Screen;
import com.example.Theatre.entity.Seat;
import com.example.Theatre.entity.Theatre;
import com.example.Theatre.repository.ScreenRepository;
import com.example.Theatre.repository.SeatRepository;
import com.example.Theatre.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class TheatreService {


    private final TheatreRepository theatreRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;

    public Theatre addTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Theatre getTheatre(Long id) {
        return theatreRepository.findById(id).orElseThrow();
    }

    // Screen
    public Screen addScreen(Long theatreId, Screen screen) {
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        screen.setTheatre(theatre);
        return screenRepository.save(screen);
    }

    // Seat layout
    public Seat addSeat(Long screenId, Seat seat) {
        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        seat.setScreen(screen);
        seat.setAvailable(true);
        return seatRepository.save(seat);
    }

    public List<Seat> getSeatsByScreen(Long screenId) {
        return seatRepository.findByScreenId(screenId);
    }
}
