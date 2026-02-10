package com.example.Theatre.repository;

import com.example.Theatre.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
