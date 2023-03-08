package com.travel.blog.services;

import com.travel.blog.payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);
}
