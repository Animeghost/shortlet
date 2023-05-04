package com.example.shortletBackend.service;

import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.HomeState;
import com.example.shortletBackend.enums.Status;
import com.example.shortletBackend.repositories.ApartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public void save(Apartments apartments){
        apartmentRepository.save(apartments);
    }

    public Apartments addHome(Apartments apartments, Users users){
        apartments.setHomeState(HomeState.PENDING);
        apartments.setHouseRefCode(apartments.getCountry().substring(0,2),apartmentRepository.findAll().size());
        apartments.setUsers(users);
        changeStatus(apartments,Status.UNOCCUPIED);
        return apartments;
    }

    public void changeStatus(Apartments apartments,Status status){
        apartments.setStatus(status);
        save(apartments);
    }
}
