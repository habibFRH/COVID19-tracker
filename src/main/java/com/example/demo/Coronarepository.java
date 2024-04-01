package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Coronarepository extends JpaRepository<Corona,Long>{

    static List<Corona> findByLastUpdatesBetween(LocalDateTime of, LocalDateTime of2) {
        throw new UnsupportedOperationException("Unimplemented method 'findByLastUpdatesBetween'");
    }
    
}
