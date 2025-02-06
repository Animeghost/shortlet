package com.example.shortletBackend.service.Impl;

import com.example.shortletBackend.dto.TextResponse;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    void save(Reservation reservation);
    void changeState(Reservation reservation, ReservationState state);
    List<Reservation> findAllReservationByUser(Users user);
    Optional<Reservation> findByReservationId(Long id);
    List<Reservation> findAllReservations();
    List<Reservation> getReservationByApartmentId(Long id);
    TextResponse getReservationByHomes(String email, Long id );
    Reservation updateReservation(Reservation reservation);
    TextResponse addReservation(Reservation reservation, String email, Long home_id);
}
