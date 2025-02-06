package com.example.shortletBackend.repositories;

import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByUsers(Users users);//find reservation by the user who created them
    List<Reservation> findAllByApartment_Id(long id);//find reservation by the apartment id
    List<Reservation> findAllByReservationStateIsAndApartment_IdAndUsers_Email(ReservationState reservationState, Long id, String email);

    boolean existsReservationsByReservationStateAndApartment_IdAndUsers_Email(ReservationState reservationState, Long id, String email);

    List<Reservation> findAllByApartment(Apartments apartments);
}
