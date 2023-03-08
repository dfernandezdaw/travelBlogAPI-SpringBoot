package com.travel.blog.controllers;

import com.travel.blog.payloads.CommentDTO;
import com.travel.blog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/travels/{travelId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long travelId,@RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(travelId, commentDTO), HttpStatus.CREATED);
    }
}