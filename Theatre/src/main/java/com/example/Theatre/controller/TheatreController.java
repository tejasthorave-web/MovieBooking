package com.example.Theatre.controller;

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
}
