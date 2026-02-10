package com.example.Theatre.service;



import com.example.Theatre.entity.Theatre;
import com.example.Theatre.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class TheatreService {


    private final TheatreRepository theatreRepository;


    public Theatre addTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }


    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }


    public Theatre getTheatre(Long id) {
        return theatreRepository.findById(id).orElseThrow();
    }
}
