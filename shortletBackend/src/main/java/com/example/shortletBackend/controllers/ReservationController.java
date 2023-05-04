package com.example.shortletBackend.controllers;

import com.example.shortletBackend.dto.ReservationDTO;
import com.example.shortletBackend.dto.TextResponse;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.PropertyType;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.enums.Status;
import com.example.shortletBackend.repositories.ApartmentRepository;
import com.example.shortletBackend.repositories.ReservationRepository;
import com.example.shortletBackend.repositories.UserRepository;
import com.example.shortletBackend.service.ApartmentService;
import com.example.shortletBackend.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@Slf4j
//@RequestMapping("/api/")
public class ReservationController {
    private final ReservationService reservationService;
    private final ApartmentService apartmentService;
    private final ReservationRepository reservationRepo;
    private final ApartmentRepository apartmentRepo;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final TextResponse customResponse;

    @GetMapping("/reservation")
    public ResponseEntity getAllReservation() {
        return ResponseEntity.ok(reservationRepo.findAll());
    }

    @GetMapping("/home/reservation/")
    public ResponseEntity getReservationByHomes(@RequestHeader("email") String email, @RequestParam("apartment_id") long id) {
//        TODO check if the user is the owner of the room #issue1
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>(reservationRepo.findAllByApartment(apartmentRepo.findById(id).get()));
        for (Reservation reserve : reservations) {

            reservationDTOS.add(mapper.map(reserve, ReservationDTO.class));
        }
        return ResponseEntity.ok(reservationDTOS);

    }


    @PutMapping("/reservation/state/")
    public ResponseEntity changeReservationState(@RequestParam("reservation_id") long id, @RequestBody Reservation reservation) {
        Optional<Reservation> oldReservation = reservationRepo.findById(id);
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
    public ResponseEntity cancelReservation(@RequestParam("reservation_id") long reservation_id, @RequestParam("email") String email) {
        Optional<Users> user = userRepository.findUsersByEmail(email);
        Optional<Reservation> reservation = reservationRepo.findById(reservation_id);
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
    public ResponseEntity startTrip(@RequestParam("reservation_id") long reservation_id, @RequestParam("email") String email) {
        Optional<Users> users = userRepository.findUsersByEmail(email);
        Optional<Reservation> reservation = reservationRepo.findById(reservation_id);
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
    public ResponseEntity endTrip(@RequestParam("reservation_id") long reservation_id, @RequestParam("email") String email) {
        Optional<Users> users = userRepository.findUsersByEmail(email);
        Optional<Reservation> reservation = reservationRepo.findById(reservation_id);
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
