package com.example.shortletBackend.controllers;

import com.example.shortletBackend.dto.TextResponse;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.enums.Status;
import com.example.shortletBackend.service.ApartmentService;
import com.example.shortletBackend.service.ReservationService;
import com.example.shortletBackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin()
@AllArgsConstructor
@Slf4j
public class ReservationController {
    private final ReservationService reservationService;
    private final ApartmentService apartmentService;
    private final UserService userService;
    private final TextResponse customResponse;

    @GetMapping("/reservation")
    public ResponseEntity getAllReservation() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

    @GetMapping("/home/reservation/")
    public ResponseEntity getReservationByHomes(Principal principal, @RequestParam("apartment_id") long id) {
//        TODO check if the user is the owner of the room #issue1
        TextResponse response = reservationService.getReservationByHomes(principal.getName(), id);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());

    }

    @PutMapping("/reservation/state/")
    public ResponseEntity changeReservationState(@RequestParam("reservation_id") long id, @RequestBody Reservation reservation) {
        Optional<Reservation> oldReservation = reservationService.findByReservationId(id);
        if (oldReservation != null) {
            reservationService.changeState(oldReservation.get(),oldReservation.get().getReservationState());
            return ResponseEntity.ok("Successfully changed the status to " + oldReservation.get().getReservationState());
        }
        customResponse.setMessage("This reservation does not exist");
        return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
    }

    //when you want to cancel a reservation
    //both the reserver and the host can cancel a reservation
    @PutMapping("/reservation/state/cancel")
    public ResponseEntity cancelReservation(@RequestParam("reservation_id") long reservation_id, Principal principal) {
        Optional<Users> user = userService.findUserByEmail(principal.getName());
        Optional<Reservation> reservation = reservationService.findByReservationId(reservation_id);
        Apartments apartments = reservation.get().getApartment();
        //this checks if it's the reserver
        if ((reservation != null && reservation.get().getUsers() == user.get()) ||
                //checks if it's the host
                (apartments.getUsers() == user.get())) {
            //checks to see if the user is the creator of the reservation or the owner of the apartment
            reservationService.changeState(reservation.get(),ReservationState.CANCELLED);
            customResponse.setMessage("The reservation has been cancelled " + reservation.get());
            return ResponseEntity.ok(customResponse);
        } else {
            customResponse.setMessage("You are not allowed to perform this");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customResponse);
        }

    }

    //when you check in to the house
    @PutMapping("/reservation/state/start")
    public ResponseEntity startTrip(@RequestParam("reservation_id") long reservation_id, Principal principal) {
        Optional<Users> users = userService.findUserByEmail(principal.getName());
        Optional<Reservation> reservation = reservationService.findByReservationId(reservation_id);
        Apartments apartments = reservation.get().getApartment();

        if (apartments != null && apartments.getUsers() == users.get()) {
            reservationService.changeState(reservation.get(),ReservationState.STARTED);
            apartmentService.changeStatus(apartments,Status.OCCUPIED);
            customResponse.setMessage("The trip has started and the home is now occupied");
            return ResponseEntity.ok(customResponse);
        } else {
            customResponse.setMessage("Only the owner is allowed to start a trip");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customResponse);
        }
    }

    //when the user is checking out of the house
    @PutMapping("/reservation/state/end")
    public ResponseEntity endTrip(@RequestParam("reservation_id") long reservation_id, Principal principal) {
        Optional<Users> users = userService.findUserByEmail(principal.getName());
        Optional<Reservation> reservation = reservationService.findByReservationId(reservation_id);
        Apartments apartments = reservation.get().getApartment();

        if ((apartments != null && apartments.getUsers() == users.get()) ||
                (reservation != null && reservation.get().getUsers() == users.get())) {
            reservationService.changeState(reservation.get(),ReservationState.COMPLETED);
            apartmentService.changeStatus(apartments,Status.UNOCCUPIED);
            customResponse.setMessage("The trip has ended and the home is now unoccupied");
            return ResponseEntity.ok(customResponse);
        } else {
            customResponse.setMessage("You are not allowed to do this");
            return new ResponseEntity<>(customResponse, HttpStatus.FORBIDDEN);//ok("The trip has ended and the home is now unoccupied");
        }
    }


}
