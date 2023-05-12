package com.example.shortletBackend.controllers;

import com.example.shortletBackend.entities.Comments;
import com.example.shortletBackend.entities.Review;
import com.example.shortletBackend.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/")
public class ReviewController {
    private final ReviewService reviewService;



    @PostMapping("/apartment/comment/add/")
    public ResponseEntity addComment(@RequestParam("apartment_id")long id, @RequestBody Comments comments
            , @RequestHeader("user_email")String email){
        return reviewService.addComment(email, comments, id);
    }

    @PostMapping("/apartment/review/add/")
    public ResponseEntity addReview(@RequestHeader("user_email")String email, @RequestParam("apartment_id")long id
            , @RequestBody Review review){
<<<<<<< HEAD
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
=======
       return reviewService.addRating(email,review,id);
>>>>>>> 3eb61ba60d39269f02e54c6f1dbdcef1bcd224ee

    }


}

