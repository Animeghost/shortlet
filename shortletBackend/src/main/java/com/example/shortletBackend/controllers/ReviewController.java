package com.example.shortletBackend.controllers;

import com.example.shortletBackend.entities.Comments;
import com.example.shortletBackend.entities.Review;
import com.example.shortletBackend.service.ReviewServiceImpl;
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
    private final ReviewServiceImpl reviewServiceImpl;

    @PostMapping("/apartment/comment/add/")
    public ResponseEntity addComment(@RequestParam("apartment_id")long id, @RequestBody Comments comments
            , Principal principal, @RequestParam("reservation_id") long reservation_id){
        return reviewServiceImpl.addComment(principal.getName(), comments, id,reservation_id);
    }

    @PostMapping("/apartment/review/add/")
    public ResponseEntity addReview(Principal principal, @RequestParam("apartment_id")long id
            , @RequestBody Review review){
       return reviewServiceImpl.addRating(principal.getName(),review,id);

    }


}

