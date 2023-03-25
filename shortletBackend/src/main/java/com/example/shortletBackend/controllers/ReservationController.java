package com.example.shortletBackend.controllers;

import com.example.shortletBackend.dto.ReservationDTO;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.enums.Status;
import com.example.shortletBackend.repositories.ApartmentRepository;
import com.example.shortletBackend.repositories.ReservationRepository;
import com.example.shortletBackend.repositories.UserRepository;
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
public class ReservationController {
    private final ReservationRepository reservationRepo;
    private final ApartmentRepository apartmentRepo;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @GetMapping("/reservation")
    public ResponseEntity getAllReservation() {
        return ResponseEntity.ok(reservationRepo.findAll());
    }

    @GetMapping("/home/reservation/")
    public ResponseEntity getReservationByHomes(@RequestParam("email") String email, @RequestParam("apartment_id") long id) {
        ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>(reservationRepo.findAllByApartment(apartmentRepo.findById(id).get()));
        for (Reservation reserve : reservations
        ) {

            reservationDTOS.add(mapper.map(reserve, ReservationDTO.class));
        }
        return ResponseEntity.ok(reservationDTOS);

    }

//    @GetMapping(value = "/reservation/availability/")
//    public ResponseEntity checkAvailability(@RequestParam("apartment_id") long id, @RequestBody Reservation reservation) {
//        log.info("{} {}", reservation.getCheckInDate(), reservation.getCheckOutDate());
//
////        if (!reservationRepo.existsByCheckInDateIsBetweenAndApartment_Id(reservation.getCheckInDate(),
////                reservation.getCheckOutDate(), id)) {
////            if (!(reservationRepo.gggggggg(reservation.getCheckInDate(), reservation.getCheckOutDate(),
////                    id) == 0L)) {
////                return ResponseEntity.ok("the two dates are free");
////            } else {
////                return ResponseEntity.status(HttpStatus.CONFLICT).body("there is already a check out date in this range");
////            }
////        } else {
////            return ResponseEntity.status(HttpStatus.CONFLICT).body("there already a check in date within this range");
////        }
//
//
//    }

    @PutMapping("/reservation/state/")
    public ResponseEntity changeReservationState(@RequestParam("reservation_id") long id, @RequestBody Reservation reservation) {
        Optional<Reservation> oldReservation = reservationRepo.findById(id);
        if (oldReservation != null) {
            oldReservation.get().setReservationState(reservation.getReservationState());
            reservationRepo.save(oldReservation.get());
            return ResponseEntity.ok("Successfully changed the status to " + oldReservation.get().getReservationState());
        }
        return new ResponseEntity<>("This reservation does not exist", HttpStatus.NOT_FOUND);
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
            reservation.get().setReservationState(ReservationState.CANCELLED);
            reservationRepo.save(reservation.get());
            return ResponseEntity.ok("The reservation has been cancelled " + reservation.get());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to perform this");
        }

    }

    //when you check in to the house
    @PutMapping("/reservation/state/start")
    public ResponseEntity startTrip(@RequestParam("reservation_id") long reservation_id, @RequestParam("email") String email) {
        Optional<Users> users = userRepository.findUsersByEmail(email);
        Optional<Reservation> reservation = reservationRepo.findById(reservation_id);
        Apartments apartments = reservation.get().getApartment();

        if (apartments != null && apartments.getUsers() == users.get()) {
            reservation.get().setReservationState(ReservationState.STARTED);
            apartments.setStatus(Status.OCCUPIED);
            reservationRepo.save(reservation.get());
            apartmentRepo.save(apartments);
            return ResponseEntity.ok("The trip has started and the home is now occupied");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only the owner is allowed to start a trip");
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
            reservation.get().setReservationState(ReservationState.COMPLETED);
            apartments.setStatus(Status.UNOCCUPIED);
            reservationRepo.save(reservation.get());
            apartmentRepo.save(apartments);
            return ResponseEntity.ok("The trip has ended and the home is now unoccupied");
        } else {
            return new ResponseEntity<>("You are not allowed to do this", HttpStatus.FORBIDDEN);//ok("The trip has ended and the home is now unoccupied");
        }
    }


}
