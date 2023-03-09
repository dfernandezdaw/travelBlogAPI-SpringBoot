package com.travel.blog.controllers;

import com.travel.blog.payloads.CommentDTO;
import com.travel.blog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/travels/{travelId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "travelId") long travelId, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(travelId, commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/travels/{travelId}/comments")
    public List<CommentDTO> getAllCommentsByTravelId(@PathVariable Long travelId) {
        return commentService.getAllCommentsByTravelId(travelId);
    }

    @GetMapping("/travels/{travelId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long travelId, @PathVariable Long commentId) {
        CommentDTO commentDTO = commentService.getCommentById(travelId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
}
