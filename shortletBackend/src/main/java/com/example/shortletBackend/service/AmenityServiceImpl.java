package com.example.shortletBackend.service;

import com.example.shortletBackend.entities.Amenities;
import com.example.shortletBackend.repositories.AmenitiesRepository;
import com.example.shortletBackend.service.Impl.AmenityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmenityServiceImpl implements AmenityService {
    private final AmenitiesRepository amenitiesRepository;


    @Override
    public Amenities saveAmenity() {
        return null;
    }
}
