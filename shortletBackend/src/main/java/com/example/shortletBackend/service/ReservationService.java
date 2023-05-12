package com.example.shortletBackend.service;

import com.example.shortletBackend.dto.ReservationDTO;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final ApartmentService apartmentService;
    private final ModelMapper mapper;


    public void save(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public void changeState(Reservation reservation, ReservationState state){
        reservation.setReservationState(state);
        save(reservation);
    }

    public Optional<Reservation> findByReservationId(long id){
        return reservationRepository.findById(id);
    }
    public List<Reservation> findAllReservations(){
        return reservationRepository.findAll();
    }

    public ArrayList getReservationByApartmentId(long id){
        return reservationRepository.findAllByApartment_Id(id);
    }

    public ResponseEntity getReservationByHomes(String email, long id ){
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Object reserve : getReservationByApartmentId(id)) {

            reservationDTOS.add(mapper.map(reserve, ReservationDTO.class));
        }
        return ResponseEntity.ok(reservationDTOS);

    }


}
