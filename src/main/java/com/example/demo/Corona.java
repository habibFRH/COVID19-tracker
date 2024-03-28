package com.example.demo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
@Entity

public class Corona {
    @Id
    @Column(name = "id",updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; 

    String combinedkey;
    Long active ; 
    Long recovered; 
    Long confirmed;
    LocalDateTime lastUpdates ; 

}
