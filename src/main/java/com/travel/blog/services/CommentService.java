package com.travel.blog.services;

import com.travel.blog.payloads.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long travelId, CommentDTO commentDTO);

    List<CommentDTO> getAllCommentsByPostId(long travelId);
}
