package com.example.shortletBackend.controllers;

import com.example.shortletBackend.dto.ApartmentForListing;
import com.example.shortletBackend.dto.ReservationTableDTO;
import com.example.shortletBackend.dto.TextResponse;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.enums.Role;
import com.example.shortletBackend.repositories.ReservationRepository;
import com.example.shortletBackend.repositories.UserRepository;
import com.example.shortletBackend.service.ApartmentServiceImpl;
import com.example.shortletBackend.service.MailService;
import com.example.shortletBackend.service.ReservationServiceImpl;
import com.example.shortletBackend.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;
    private final ApartmentServiceImpl apartmentServiceImpl;
    private final ReservationRepository reservationRepo;
    private final ReservationServiceImpl reservationServiceImpl;
    private final MailService mailService;
    private final ModelMapper mapper;
    private final TextResponse customResponse ;



    @GetMapping("/")
    public ResponseEntity getUser(Principal principal) {
        return ResponseEntity.ok(userServiceImpl.findUserByEmail(principal.getName()));
    }

    //get all user with role staff
    @GetMapping("/staff")
    public ResponseEntity getAllStaff(){
        return ResponseEntity.ok(userServiceImpl.findAllUsersByRole(Role.STAFF));
    }

    //get all user with role user
    @GetMapping("/user")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userServiceImpl.findAllUsersByRole(Role.USER));
    }



//    get all user with admin role
    @GetMapping("/admin")
    public ResponseEntity getAllAdminUsers(){

        return ResponseEntity.ok(userServiceImpl.findAllUsersByRole(Role.ADMIN));
    }
    //make an admin a user
    @PutMapping("/user/update/role/")
    public ResponseEntity removeAnAdminUser(Principal principal
            , @RequestParam("user_id") long id) throws MessagingException {


        Optional<Users> admin_user = userServiceImpl.findUserByEmail(principal.getName());
        Optional<Users> users= userServiceImpl.findUsersById(id);
        if (admin_user.isPresent() && users.isPresent()){
            if (admin_user.get().getRole() == Role.ADMIN){
                users.get().setRole(Role.STAFF);
                userServiceImpl.save(users.get());
                mailService.sendHtmlMessage(users.get().getEmail(),"Administrative Status Revoked"
                        ,"Your administrative status has been revoked,reach out to the super admin to find out" +
                                " why this happened and if it can be changed . \n Till then you are now a plain ol user" +
                                " enjoy the rest of your day", users.get().getName(),"/index.html");
                customResponse.setMessage("User "+users.get().getName()+" is no longer an admin");
                return ResponseEntity.ok(customResponse);

            }else {
                customResponse.setMessage("User does not have permission to do this");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customResponse);
            }
        }else {
            customResponse.setMessage("User doesn't exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);

        }

    }

    @PutMapping("/user/update/")
    public ResponseEntity createAnAdminUser(Principal principal
            , @RequestParam("user_id") long id) throws MessagingException {
        Optional<Users> admin_user = userServiceImpl.findUserByEmail(principal.getName());
        Optional<Users> users= userServiceImpl.findUsersById(id);
        if (admin_user.isPresent() && users.isPresent()){
            if (admin_user.get().getRole() == Role.ADMIN && users.get().getRole() == Role.STAFF){
                users.get().setRole(Role.ADMIN);
                userServiceImpl.save(users.get());
//
                mailService.sendHtmlMessage(users.get().getEmail(),"Promotion",
                        "You are receiving this message because you have being promoted to an administrator.",users.get().getName(),"/index.html");
                customResponse.setMessage("User "+users.get().getName()+" has been made an admin");
                return ResponseEntity.ok(customResponse);
            }else {
                customResponse.setMessage("User does not have permission to do this");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customResponse);
            }
        }else {
            customResponse.setMessage("User doesn't exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);

        }

    }



    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody Users users){
        Users newUser= userServiceImpl.addUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/update_user/")
    public ResponseEntity updateUser(Principal principal,
         @RequestBody Users users){
        TextResponse response = userServiceImpl.updateUser(principal.getName(), users);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }


    @PutMapping("/addReservation/")
    public ResponseEntity addReservation(@RequestBody Reservation reservation,
         Principal principal,@RequestParam("apartment_id") long home_id){
        TextResponse response = reservationServiceImpl.addReservation(reservation,principal.getName(),home_id);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());

    }
    // todo this has some repo calls
    @GetMapping("/reservation/")
    public ResponseEntity getAllUserReservation(Principal principal){
        ArrayList reservationDTOS = new ArrayList<>();
        for (Reservation reservation: reservationServiceImpl.findAllReservationByUser(userServiceImpl.findUserByEmail(principal.getName()).get())){
            if (reservation.getCheckInDate().before(new Date())){
                reservation.setReservationState(ReservationState.STARTED);
            }
            if (reservation.getCheckOutDate().before(new Date())){
                reservation.setReservationState(ReservationState.COMPLETED);
            }
            reservationServiceImpl.save(reservation);
            ReservationTableDTO old= mapper.map(reservation, ReservationTableDTO.class);
            old.setApartmentPicture(reservation.getApartment().getPictures().stream().findFirst().get().getUrl());
            reservationDTOS.add(old);
        }
        return ResponseEntity.ok(reservationDTOS);


    }

    @GetMapping("/user/listings/")
    public ResponseEntity getAllUserHouses(Principal principal){
        Optional<Users> users= userServiceImpl.findUserByEmail(principal.getName());
        if (users == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user does not exist");
        }else {
            ArrayList<ApartmentForListing> apartmentDto= new ArrayList<>();
            for (Apartments apartments: apartmentServiceImpl.findByUser(users.get()) ){
                ApartmentForListing listing = mapper.map(apartments,ApartmentForListing.class);
                listing.setPictures(apartments.getPictures().stream().findFirst().get());
                apartmentDto.add(listing);
            }
            return ResponseEntity.status(200).body(apartmentDto);
        }

    }

    @GetMapping("/user/disable/")
    public ResponseEntity disableUser(Principal principal,@RequestParam("disabledUserId") long disabledUserId){
        TextResponse response = userServiceImpl.disableUser(disabledUserId,principal.getName());
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }

    @GetMapping("/user/enable/")
    public ResponseEntity enableUser(Principal principal,@RequestParam("user_id") long enabledUserId){
        TextResponse response = userServiceImpl.enableUser(enabledUserId,principal.getName());
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }


}
