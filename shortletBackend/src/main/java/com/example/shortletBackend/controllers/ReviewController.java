package com.example.shortletBackend.controllers;

import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Comments;
import com.example.shortletBackend.entities.Review;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static java.lang.Math.log;

@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;


    @PostMapping("/apartment/comment/add/")
    public ResponseEntity addComment(@RequestParam("apartment_id")long id, @RequestBody Comments comments
            , @RequestHeader("user_email")String email){
        Optional<Users> users = userRepository.findUsersByEmail(email);
        if ( users.isPresent()) {
//            if (reservationRepository.existsReservationsByReservationStateAndApartment_IdAndUsers_Email(ReservationState.COMPLETED,id,email)){
                
                Optional<Apartments> apartments=apartmentRepository.findById(id);
                comments.setCommentDate(new Date());
                comments.setUsers(users.get());
                comments.setApartments(apartments.get());


                apartmentRepository.save(apartments.get());
                commentRepository.save(comments);//save a comment
                userRepository.save(users.get());
                return ResponseEntity.ok(apartments.get());
//            }else {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("this user hasn't completed a stayed at the house" +
//                        " as such can't comment ");
//            }


        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user doesn't exist");
        }

    }

    @PostMapping("/apartment/review/add/")
    public ResponseEntity addReview(@RequestHeader("user_email")String email, @RequestParam("apartment_id")long id
            , @RequestBody Review review){
        Optional<Users> users = userRepository.findUsersByEmail(email);
        if ( users.isPresent()) {
            if (reservationRepository.existsReservationsByReservationStateAndApartment_IdAndUsers_Email(ReservationState.COMPLETED,id,email)){
                double rating = 0 ;
                Optional<Apartments> apartments=apartmentRepository.findById(id);
                review.setUsers(users.get());
                review.setApartments(apartments.get());


                reviewRepository.save(review);//save a comment
                userRepository.save(users.get());

                ArrayList<Review> ratingList = reviewRepository.findAllByApartments_Id(id);
                for (Review ratingScore: ratingList
                ) {
                    rating += (ratingScore.getReview()/5);
                }
                double ratingPercentage = ((rating) / ratingList.size()) * 5.0;

                BigDecimal newRating=new BigDecimal(ratingPercentage).setScale(2, RoundingMode.HALF_UP);

                apartments.get().setRating(newRating.doubleValue());
                apartmentRepository.save(apartments.get());
                return ResponseEntity.ok(apartments.get());
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("this user hasn't completed a stayed at the house" +
                        " as such can't give an accurate rating ");
            }


        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user doesn't exist");
        }

    }


}

