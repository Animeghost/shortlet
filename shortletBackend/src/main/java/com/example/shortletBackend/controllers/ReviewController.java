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
       return reviewService.addRating(email,review,id);

    }


}

