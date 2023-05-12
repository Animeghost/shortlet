package com.example.shortletBackend.service;

import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.HomeState;
import com.example.shortletBackend.enums.Status;
import com.example.shortletBackend.repositories.ApartmentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ModelMapper mapper;

    public void save(Apartments apartments){
        apartmentRepository.save(apartments);
    }
    public Optional<Apartments> findById(long id){
        return apartmentRepository.findById(id);
    }
    public List<Apartments> findByUser(Users users){
        return apartmentRepository.findAllByUsers(users);
    }

    public List<Apartments> findAllApartments(){
        return apartmentRepository.findAll();
    }
    public List findAllApartmentByHomeState(HomeState state, Class dto){
        ArrayList hotelList = new ArrayList<>();
        for (Apartments hotel:apartmentRepository.findAllByHomeStateIs(state)
        ) {
            hotelList.add(mapper.map(hotel, dto));
        }
        return hotelList;
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
