package com.example.shortletBackend.service;

import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Comments;
import com.example.shortletBackend.entities.Review;
import com.example.shortletBackend.entities.Users;
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
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ApartmentService apartmentService;
    private final ReservationRepository reservationRepository;



    public ResponseEntity addComment(String email, Comments comments,long id){
        Optional<Users> users = userService.findUserByEmail(email);
        if ( users.isPresent()) {
//            if (reservationRepository.existsReservationsByReservationStateAndApartment_IdAndUsers_Email(ReservationState.COMPLETED,id,email)){

            Optional<Apartments> apartments=apartmentService.findById(id);
            comments.setCommentDate(new Date());
            comments.setUsers(users.get());
            comments.setApartments(apartments.get());


            apartmentService.save(apartments.get());
            commentRepository.save(comments);//save a comment
            userService.save(users.get());
            return ResponseEntity.ok(apartments.get());
//            }else {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("this user hasn't completed a stayed at the house" +
//                        " as such can't comment ");
//            }


        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user doesn't exist");
        }

    }

    public ResponseEntity addRating(String email, Review review,long id){
        Optional<Users> users = userService.findUserByEmail(email);
        if ( users.isPresent()) {
            if (reservationRepository.existsReservationsByReservationStateAndApartment_IdAndUsers_Email(ReservationState.COMPLETED,id,email)){
                double rating = 0 ;
                Optional<Apartments> apartments=apartmentService.findById(id);
                review.setUsers(users.get());
                review.setApartments(apartments.get());


                reviewRepository.save(review);//save a comment
                userService.save(users.get());

                ArrayList<Review> ratingList = reviewRepository.findAllByApartments_Id(id);
                for (Review ratingScore: ratingList
                ) {
                    rating += (ratingScore.getReview()/5);
                }
                double ratingPercentage = ((rating) / ratingList.size()) * 5.0;

                BigDecimal newRating=new BigDecimal(ratingPercentage).setScale(2, RoundingMode.HALF_UP);

                apartments.get().setRating(newRating.doubleValue());
                apartmentService.save(apartments.get());
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
