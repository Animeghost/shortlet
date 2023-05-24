package com.example.shortletBackend.controllers;

import com.example.shortletBackend.entities.Comments;
import com.example.shortletBackend.entities.Review;
import com.example.shortletBackend.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;



    @PostMapping("/apartment/comment/add/")
    public ResponseEntity addComment(@RequestParam("apartment_id")long id, @RequestBody Comments comments
            , Principal principal, @RequestParam("reservation_id") long reservation_id){
        return reviewService.addComment(principal.getName(), comments, id,reservation_id);
    }

    @PostMapping("/apartment/review/add/")
    public ResponseEntity addReview(Principal principal, @RequestParam("apartment_id")long id
            , @RequestBody Review review){
       return reviewService.addRating(principal.getName(),review,id);

    }


}

