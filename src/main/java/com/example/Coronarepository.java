package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Corona;

@Repository
public interface Coronarepository extends JpaRepository<Corona,Long>{
    
}
