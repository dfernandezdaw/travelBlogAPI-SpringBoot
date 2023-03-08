package com.travel.blog.services.impl;

import com.travel.blog.exceptions.ResourceNotFoundException;
import com.travel.blog.models.Comment;
import com.travel.blog.models.Travel;
import com.travel.blog.payloads.CommentDTO;
import com.travel.blog.repositories.CommentRepository;
import com.travel.blog.repositories.TravelRepository;
import com.travel.blog.services.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentSerciceImpl implements CommentService {

    private CommentRepository commentRepository;
    private TravelRepository travelRepository;

    public CommentSerciceImpl(CommentRepository commentRepository, TravelRepository travelRepository) {
        this.commentRepository = commentRepository;
        this.travelRepository = travelRepository;
    }

    @Override
    public CommentDTO createComment(Long travelId, CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        //Retrieve travel by id
        Travel travel = travelRepository.findById(travelId).orElseThrow(
                () -> new ResourceNotFoundException("Travel", "id", travelId));
        //Set travel to comment
        comment.setTravel(travel);
        //Save comment
        Comment newComment = commentRepository.save(comment);
        return convertToDTO(newComment);
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setComment(comment.getComment());
        return commentDTO;
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setComment(commentDTO.getComment());
        return comment;
    }
}
