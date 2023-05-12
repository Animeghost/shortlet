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
import com.example.shortletBackend.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ApartmentService apartmentService;
    private final ReservationRepository reservationRepo;
    private final MailService mailService;
    private final ModelMapper mapper;
    private final TextResponse customResponse ;


    @GetMapping("/")
    public ResponseEntity getUser(@RequestHeader("user_email")String email){
        return ResponseEntity.of(userService.findUserByEmail(email));
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
    public ResponseEntity removeAnAdminUser(@RequestHeader("admin_email")String email
            , @RequestParam("user_id") long id) throws MessagingException {
        Optional<Users> admin_user =userService.findUserByEmail(email);
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
    public ResponseEntity createAnAdminUser(@RequestHeader("admin_email")String email
            , @RequestParam("user_id") long id) throws MessagingException {
        Optional<Users> admin_user =userService.findUserByEmail(email);
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
    public ResponseEntity updateUser(@RequestHeader("user_email") String email,
         @RequestBody Users users){
        return userService.updateUser(email, users);
    }

    @PutMapping("/addReservation/")
    public ResponseEntity addReservation(@RequestBody Reservation reservation,
         @RequestParam("user_email")String email,@RequestParam("apartment_id") long home_id){
        Optional<Users> user = userService.findUserByEmail(email);
        Optional<Apartments> apartments= apartmentService.findById(home_id);

        if(user.isPresent()){
            if(apartments.isPresent()){

                reservation.setUsers(user.get());
                reservation.setApartment(apartments.get());
                reservation.setReservationState(ReservationState.PENDING);
                user.get().getReservationSet().add(reservation);
                apartments.get().getReservations().add(reservation);

                apartmentService.save(apartments.get());
                reservationRepo.save(reservation);
                userRepository.save(user.get());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservation);
            }else {
                customResponse.setMessage("The house can not be found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
            }
        }else {
            customResponse.setMessage("The user can not be found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
        }

    }

    @GetMapping("/reservation/")
    public ResponseEntity getAllUserReservation(@RequestHeader("user_email")String email){
        ArrayList reservationDTOS = new ArrayList<>();
        for (Reservation reservation: reservationRepo.findAllByUsers(userRepository.findUsersByEmail(email).get())){
            ReservationTableDTO old= mapper.map(reservation, ReservationTableDTO.class);
            old.setApartmentPicture(reservation.getApartment().getPictures().stream().findFirst().get().getUrl());
            reservationDTOS.add(old);
        }
        return ResponseEntity.ok(reservationDTOS);


    }

    @GetMapping("/user/listings/")
    public ResponseEntity getAllUserHouses(@RequestHeader("user_email") String email){
        Optional<Users> users= userRepository.findUsersByEmail(email);
        if (users == null) {
            customResponse.setMessage("The user does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
        }else {
            ArrayList<ApartmentForListing> apartmentDto= new ArrayList<>();
            for (Apartments apartments:apartmentService.findByUser(users.get()) ){
                ApartmentForListing listing = mapper.map(apartments,ApartmentForListing.class);
                listing.setPictures(apartments.getPictures().stream().findFirst().get());
                apartmentDto.add(listing);
            }
            return ResponseEntity.ok(apartmentDto) ;
        }


    }





}
