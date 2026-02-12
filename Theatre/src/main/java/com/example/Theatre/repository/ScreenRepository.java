package com.example.Theatre.repository;

import com.example.Theatre.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findByTheatreId(Long theatreId);
}
