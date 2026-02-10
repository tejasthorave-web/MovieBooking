package com.example.Theatre.controller;

import com.example.Theatre.entity.Screen;
import com.example.Theatre.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/screens")
@RequiredArgsConstructor
public class ScreenController {


    private final ScreenService screenService;


    @PostMapping
    public Screen addScreen(@RequestBody Screen screen) {
        return screenService.addScreen(screen);
    }


    @GetMapping("/theatre/{theatreId}")
    public List<Screen> getScreensByTheatre(@PathVariable Long theatreId) {
        return screenService.getScreensByTheatre(theatreId);
    }
}
