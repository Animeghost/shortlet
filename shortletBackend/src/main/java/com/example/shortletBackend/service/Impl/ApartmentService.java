package com.example.shortletBackend.service.Impl;

import com.example.shortletBackend.dto.ApartmentsDTO;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.HomeState;
import com.example.shortletBackend.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    void save(Apartments apartment);
    Optional<Apartments> findById(Long id);
    List<Apartments> findByUser(Users user);
    List<Apartments> findAllApartments();
    List<Apartments> findAllApartmentByHomeState(HomeState state, Class dto);
    List<ApartmentsDTO> getAllVerifiedHomesWithNumberOfGuest(int number);
    Apartments addHome(Apartments apartments, Users users);
    void changeStatus(Apartments apartments, Status status);
    String deleteApartment(long id,String email);
}
