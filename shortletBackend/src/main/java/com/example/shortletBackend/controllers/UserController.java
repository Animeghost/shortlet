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
import com.example.shortletBackend.service.ApartmentService;
import com.example.shortletBackend.service.MailService;
import com.example.shortletBackend.service.ReservationService;
import com.example.shortletBackend.service.UserService;
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
    private final UserService userService;
    private final UserRepository userRepository;
    private final ApartmentService apartmentService;
    private final ReservationRepository reservationRepo;
    private final ReservationService reservationService;
    private final MailService mailService;
    private final ModelMapper mapper;
    private final TextResponse customResponse ;



    @GetMapping("/")
    public ResponseEntity getUser(Principal principal) {
        return ResponseEntity.ok(userService.findUserByEmail(principal.getName()));
    }

    //get all user with role staff
    @GetMapping("/staff")
    public ResponseEntity getAllStaff(){
        return ResponseEntity.ok(userService.findAllUsersByRole(Role.STAFF));
    }

    //get all user with role user
    @GetMapping("/user")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.findAllUsersByRole(Role.USER));
    }



//    get all user with admin role
    @GetMapping("/admin")
    public ResponseEntity getAllAdminUsers(){

        return ResponseEntity.ok(userService.findAllUsersByRole(Role.ADMIN));
    }
    //make an admin a user
    @PutMapping("/user/update/role/")
    public ResponseEntity removeAnAdminUser(Principal principal
            , @RequestParam("user_id") long id) throws MessagingException {


        Optional<Users> admin_user =userService.findUserByEmail(principal.getName());
        Optional<Users> users=userService.findUsersById(id);
        if (admin_user.isPresent() && users.isPresent()){
            if (admin_user.get().getRole() == Role.ADMIN){
                users.get().setRole(Role.STAFF);
                userService.save(users.get());
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
        Optional<Users> admin_user =userService.findUserByEmail(principal.getName());
        Optional<Users> users=userService.findUsersById(id);
        if (admin_user.isPresent() && users.isPresent()){
            if (admin_user.get().getRole() == Role.ADMIN && users.get().getRole() == Role.STAFF){
                users.get().setRole(Role.ADMIN);
                userService.save(users.get());
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
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(users));
    }

    @PutMapping("/update_user/")
    public ResponseEntity updateUser(Principal principal,
         @RequestBody Users users){
        TextResponse response = userService.updateUser(principal.getName(), users);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }


    @PutMapping("/addReservation/")
    public ResponseEntity addReservation(@RequestBody Reservation reservation,
         Principal principal,@RequestParam("apartment_id") long home_id){
        TextResponse response = reservationService.addReservation(reservation,principal.getName(),home_id);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());

    }

    @GetMapping("/reservation/")
    public ResponseEntity getAllUserReservation(Principal principal){
        ArrayList reservationDTOS = new ArrayList<>();
        for (Reservation reservation: reservationRepo.findAllByUsers(userRepository.findUsersByEmail(principal.getName()).get())){
            if (reservation.getCheckInDate().before(new Date())){
                reservation.setReservationState(ReservationState.STARTED);
            }
            if (reservation.getCheckOutDate().before(new Date())){
                reservation.setReservationState(ReservationState.COMPLETED);
            }
            reservationService.save(reservation);
            ReservationTableDTO old= mapper.map(reservation, ReservationTableDTO.class);
            old.setApartmentPicture(reservation.getApartment().getPictures().stream().findFirst().get().getUrl());
            reservationDTOS.add(old);
        }
        return ResponseEntity.ok(reservationDTOS);


    }

    @GetMapping("/user/listings/")
    public ResponseEntity getAllUserHouses(Principal principal){
        Optional<Users> users= userService.findUserByEmail(principal.getName());
        if (users == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user does not exist");
        }else {
            ArrayList<ApartmentForListing> apartmentDto= new ArrayList<>();
            for (Apartments apartments:apartmentService.findByUser(users.get()) ){
                ApartmentForListing listing = mapper.map(apartments,ApartmentForListing.class);
                listing.setPictures(apartments.getPictures().stream().findFirst().get());
                apartmentDto.add(listing);
            }
            return ResponseEntity.status(200).body(apartmentDto);
        }

    }

    @GetMapping("/user/disable/")
    public ResponseEntity disableUser(Principal principal,@RequestParam("disabledUserId") long disabledUserId){
        TextResponse response = userService.disableUser(disabledUserId,principal.getName());
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }

    @GetMapping("/user/enable/")
    public ResponseEntity enableUser(Principal principal,@RequestParam("user_id") long enabledUserId){
        TextResponse response = userService.enableUser(enabledUserId,principal.getName());
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }


}
