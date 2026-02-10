package com.example.Theatre.service;



import com.example.Theatre.entity.Screen;
import com.example.Theatre.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ScreenService {


    private final ScreenRepository screenRepository;


    public Screen addScreen(Screen screen) {
        return screenRepository.save(screen);
    }


    public List<Screen> getScreensByTheatre(Long theatreId) {
        return screenRepository.findByTheatreId(theatreId);
    }
}
