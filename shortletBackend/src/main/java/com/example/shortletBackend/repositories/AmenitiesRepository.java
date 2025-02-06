package com.example.shortletBackend.repositories;

import com.example.shortletBackend.entities.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
  
}