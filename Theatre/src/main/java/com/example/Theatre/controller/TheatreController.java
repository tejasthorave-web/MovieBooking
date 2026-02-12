package com.example.Theatre.controller;

import com.example.Theatre.entity.Screen;
import com.example.Theatre.entity.Seat;
import com.example.Theatre.entity.Theatre;
import com.example.Theatre.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping
    public Theatre addTheatre(@RequestBody Theatre theatre) {
        return theatreService.addTheatre(theatre);
    }

    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{id}")
    public Theatre getTheatre(@PathVariable Long id) {
        return theatreService.getTheatre(id);
    }

    // Screen APIs
    @PostMapping("/{theatreId}/screens")
    public Screen addScreen(@PathVariable Long theatreId,
                            @RequestBody Screen screen) {
        return theatreService.addScreen(theatreId, screen);
    }

    // Seat layout APIs
    @PostMapping("/screens/{screenId}/seats")
    public Seat addSeat(@PathVariable Long screenId,
                        @RequestBody Seat seat) {
        return theatreService.addSeat(screenId, seat);
    }

    @GetMapping("/screens/{screenId}/seats")
    public List<Seat> getSeats(@PathVariable Long screenId) {
        return theatreService.getSeatsByScreen(screenId);
    }
}
