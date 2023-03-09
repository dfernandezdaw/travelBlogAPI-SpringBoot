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

    @GetMapping("/comments")
    public List<CommentDTO> getAllComments() {
        return commentService.getAllComments();
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

    @PutMapping("/travels/{travelId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long travelId,
                                                    @PathVariable Long commentId,
                                                    @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.updateComment(travelId, commentId, commentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/travels/{travelId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long travelId, @PathVariable Long commentId) {
        commentService.deleteComment(travelId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
