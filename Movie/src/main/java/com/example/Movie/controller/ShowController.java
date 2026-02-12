package com.example.Movie.controller;

import com.example.Movie.entity.Show;
import com.example.Movie.entity.ShowSeat;
import com.example.Movie.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return showService.createShow(show);
    }

    // map seats to show
    @PostMapping("/{showId}/seats")
    public String mapSeats(@PathVariable Long showId,
                           @RequestBody List<Long> seatIds) {

        showService.createShowSeats(showId, seatIds);
        return "Seats mapped to show";
    }

    // get availability
    @GetMapping("/{showId}/seats")
    public List<ShowSeat> getSeats(@PathVariable Long showId) {
        return showService.getSeatsByShow(showId);
    }

    // book seats
    @PostMapping("/{showId}/book")
    public String bookSeats(@PathVariable Long showId,
                            @RequestBody List<Long> seatIds) {

        showService.bookSeats(showId, seatIds);
        return "Seats booked successfully";
    }

}
