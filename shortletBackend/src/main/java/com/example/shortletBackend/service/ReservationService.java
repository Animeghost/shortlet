package com.example.shortletBackend.service;

import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public void save(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public void changeState(Reservation reservation, ReservationState state){
        reservation.setReservationState(state);
        save(reservation);
    }


}
