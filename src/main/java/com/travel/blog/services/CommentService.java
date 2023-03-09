package com.travel.blog.services;

import com.travel.blog.models.Comment;
import com.travel.blog.payloads.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long travelId, CommentDTO commentDTO);

    List<CommentDTO> getAllCommentsByTravelId(long travelId);

    CommentDTO getCommentById(Long travelId, Long commentId);

    CommentDTO updateComment(Long travelId, Long commentId, CommentDTO commentDTO);

    void deleteComment(Long travelId, Long commentId);
}
