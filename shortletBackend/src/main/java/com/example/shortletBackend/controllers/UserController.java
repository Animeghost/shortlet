package com.example.shortletBackend.controllers;

import com.example.shortletBackend.dto.*;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.enums.Role;
import com.example.shortletBackend.repositories.ApartmentRepository;
import com.example.shortletBackend.repositories.ReservationRepository;
import com.example.shortletBackend.repositories.UserRepository;
import com.example.shortletBackend.service.MailService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepo;
    private final ReservationRepository reservationRepo;
    private final MailService mailService;
    private final ModelMapper mapper;
    private final TextResponse customResponse ;


    @GetMapping("/")
    public ResponseEntity getUser(@RequestHeader("user_email")String email){
        return ResponseEntity.of(userRepository.findUsersByEmail(email));
    }

    //get all user with role users
    @GetMapping("/user")
    public ResponseEntity getAllNormalUsers(){
        ArrayList<UsersDTO> userList = new ArrayList<>();
        for (Users user: userRepository.findAllByRole(Role.USER)
             ) {
            userList.add(mapper.map(user, UsersDTO.class));
        }
        return ResponseEntity.ok(userList);
    }

//    get all user with admin role
    @GetMapping("/admin")
    public ResponseEntity getAllAdminUsers(){
        ArrayList<UsersDTO> userList = new ArrayList<>();
        for (Users user: userRepository.findAllByRole(Role.ADMIN)
        ) {
            userList.add(mapper.map(user, UsersDTO.class));
        }
        return ResponseEntity.ok(userList);
    }
    //make an admin a user
    @PutMapping("/user/update/role/")
    public ResponseEntity removeAnAdminUser(@RequestHeader("admin_email")String email
            , @RequestParam("user_id") long id) throws MessagingException {
        Optional<Users> admin_user =userRepository.findUsersByEmail(email);
        Optional<Users> users=userRepository.findById(id);
        if (admin_user.isPresent() && users.isPresent()){
            if (admin_user.get().getRole() == Role.ADMIN){
                users.get().setRole(Role.USER);
                userRepository.save(users.get());
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
        Optional<Users> admin_user =userRepository.findUsersByEmail(email);
        Optional<Users> users=userRepository.findById(id);
        if (admin_user.isPresent() && users.isPresent()){
            if (admin_user.get().getRole() == Role.ADMIN){
                users.get().setRole(Role.ADMIN);
                userRepository.save(users.get());
//                mailService.sendSimpleMessage(users.get().getEmail(),"New admin"
//                        ,"You have been selected to be an admin");
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
        Optional<Users> oldUser = userRepository.findUsersByEmail(users.getEmail());
        if (!oldUser.isPresent()) {//if the user does not exist it creates a new user
            users.setRole(Role.USER);
            userRepository.save(users);
            return ResponseEntity.ok(users);
        }else {// if it does exist, the user is then returned
            return ResponseEntity.ok(oldUser);
        }

    }

    @PutMapping("/update_user/")
    public ResponseEntity updateUser(@RequestHeader("user_email") String email,
         @RequestBody Users users){
        Optional<Users> oldInfo= userRepository.findUsersByEmail(email);
        if (oldInfo.isPresent()){

            if (users.getPhoneNo() != null) {
                oldInfo.get().setPhoneNo(users.getPhoneNo());
            }
            if (users.getName() != null) {
                oldInfo.get().setName(users.getName());
            }

            userRepository.save(oldInfo.get());
            return ResponseEntity.ok(mapper.map(oldInfo.get(), UsersDTO.class));
        }
        else {
            return (ResponseEntity) ResponseEntity.notFound();
        }

    }

    @PutMapping("/addReservation/")
    public ResponseEntity addReservation(@RequestBody Reservation reservation,
         @RequestParam("user_email")String email,@RequestParam("apartment_id") long home_id){
        Optional<Users> user = userRepository.findUsersByEmail(email);
        Optional<Apartments> apartments= apartmentRepo.findById(home_id);

        if(user.isPresent()){
            if(apartments.isPresent()){

                reservation.setUsers(user.get());
                reservation.setApartment(apartments.get());
                reservation.setReservationState(ReservationState.PENDING);
                user.get().getReservationSet().add(reservation);
                apartments.get().getReservations().add(reservation);

                apartmentRepo.save(apartments.get());
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
            for (Apartments apartments:apartmentRepo.findAllByUsers(users.get()) ){
                ApartmentForListing listing = mapper.map(apartments,ApartmentForListing.class);
                listing.setPictures(apartments.getPictures().stream().findFirst().get());
                apartmentDto.add(listing);
            }
            return ResponseEntity.ok(apartmentDto) ;
        }


    }





}
