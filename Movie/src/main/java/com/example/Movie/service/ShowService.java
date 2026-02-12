package com.example.Movie.service;

import com.example.Movie.entity.Show;
import com.example.Movie.entity.ShowSeat;
import com.example.Movie.repository.ShowRepository;
import com.example.Movie.repository.ShowSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;


    // 🎬 Create show timing
    public Show createShow(Show show) {
        if (show.getAvailableSeats() == null) {
            show.setAvailableSeats(100);
        }
        return showRepository.save(show);
    }

    // 🎟 Map seats for a show
    public void createShowSeats(Long showId, List<Long> seatIds) {

        for (Long seatId : seatIds) {
            ShowSeat showSeat = ShowSeat.builder()
                    .showId(showId)
                    .seatId(seatId)
                    .available(true)
                    .build();

            showSeatRepository.save(showSeat);
        }
    }

    // 🪑 Get seat availability for show
    public List<ShowSeat> getSeatsByShow(Long showId) {
        return showSeatRepository.findByShowId(showId);
    }

    // 💳 Book seats
    public void bookSeats(Long showId, List<Long> seatIds) {

        List<ShowSeat> seats = showSeatRepository.findByShowId(showId);

        for (ShowSeat seat : seats) {
            if (seatIds.contains(seat.getSeatId())) {
                seat.setAvailable(false);
                showSeatRepository.save(seat);
            }
        }
    }
}

