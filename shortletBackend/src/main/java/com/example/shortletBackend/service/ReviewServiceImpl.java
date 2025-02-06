package com.example.shortletBackend.service;

import com.example.shortletBackend.entities.*;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.repositories.CommentRepository;
import com.example.shortletBackend.repositories.ReservationRepository;
import com.example.shortletBackend.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service @AllArgsConstructor
public class ReviewServiceImpl {

    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final UserServiceImpl userServiceImpl;
    private final ApartmentServiceImpl apartmentServiceImpl;
    private final ReservationRepository reservationRepository;



    public ResponseEntity addComment(String email, Comments comments,long id,long reservation_id){
        Optional<Users> users = userServiceImpl.findUserByEmail(email);
        Optional<Reservation> reservation = reservationRepository.findById(reservation_id);
        //checks if the user exist and if they are the one who created the reservation
        if ( users.isPresent() ) {
            if ( reservation.get().getUsers() ==users.get() || reservation.get().getApartment().getUsers() == users.get()) {
                if (reservation.get().getCheckInDate().before(new Date())) {
//            if (reservationRepository.existsReservationsByReservationStateAndApartment_IdAndUsers_Email(ReservationState.COMPLETED,id,email)){
                    
                    Optional<Apartments> apartments= apartmentServiceImpl.findById(id);
                    comments.setCommentDate(new Date());
                    comments.setUsers(users.get());
                    comments.setApartments(apartments.get());


                    apartmentServiceImpl.save(apartments.get());
                    commentRepository.save(comments);//save a comment
                    userServiceImpl.save(users.get());
                    return ResponseEntity.ok(apartments.get());

                }else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(" This user hasn't stayed at the house" +
                            " as such can't comment ");
                }
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user has no relation to this reservation ");
            }

        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user doesn't exist");
        }

    }

    public ResponseEntity addRating(String email, Review review,long id){
        Optional<Users> users = userServiceImpl.findUserByEmail(email);
        if ( users.isPresent()) {
            if (reservationRepository.existsReservationsByReservationStateAndApartment_IdAndUsers_Email(ReservationState.COMPLETED,id,email)){
                double rating = 0 ;
                Optional<Apartments> apartments= apartmentServiceImpl.findById(id);
                review.setUsers(users.get());
                review.setApartments(apartments.get());


                reviewRepository.save(review);//save a comment
                userServiceImpl.save(users.get());

                ArrayList<Review> ratingList = reviewRepository.findAllByApartments_Id(id);
                for (Review ratingScore: ratingList
                ) {
                    rating += (ratingScore.getReview()/5);
                }
                double ratingPercentage = ((rating) / ratingList.size()) * 5.0;

                BigDecimal newRating=new BigDecimal(ratingPercentage).setScale(2, RoundingMode.HALF_UP);

                apartments.get().setRating(newRating.doubleValue());
                apartmentServiceImpl.save(apartments.get());
                return ResponseEntity.ok(apartments.get());
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("this user hasn't completed a stayed at the house" +
                        " as such can't comment ");
            }


        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user doesn't exist");
        }

    }

}
