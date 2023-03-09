package com.travel.blog.services.impl;

import com.travel.blog.exceptions.ResourceNotFoundException;
import com.travel.blog.exceptions.TravelBlogAPIException;
import com.travel.blog.models.Comment;
import com.travel.blog.models.Travel;
import com.travel.blog.payloads.CommentDTO;
import com.travel.blog.repositories.CommentRepository;
import com.travel.blog.repositories.TravelRepository;
import com.travel.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private TravelRepository travelRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, TravelRepository travelRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.travelRepository = travelRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment(long travelId, CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        //Retrieve travel by id
        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new ResourceNotFoundException("Travel", "id", travelId));
        //Set travel to comment
        comment.setTravel(travel);
        //Save comment
        Comment newComment = commentRepository.save(comment);
        return convertToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getAllCommentsByTravelId(long postId) {
        //Get all comments by travel id
        List<Comment> comments = commentRepository.findAllByTravelId(postId);

        //Convert list of comments to list of commentDTOs
        return comments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //Get comment x of travel y
    @Override
    public CommentDTO getCommentById(Long travelId, Long commentId) {
        Travel travel = travelRepository.findById(travelId).orElseThrow(
                () -> new ResourceNotFoundException("Travel", "id", travelId));

        //Get comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        //Check if comment belongs to travel
        if (!comment.getTravel().getId().equals(travel.getId())) {
            throw new TravelBlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to travel");
        }

        return convertToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long travelId, Long commentId, CommentDTO commentDTO) {
        //Get travel by id
        Travel travel = travelRepository.findById(travelId).orElseThrow(
                () -> new ResourceNotFoundException("Travel", "id", travelId));

        //Get comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        //Check if comment belongs to travel
        if (!comment.getTravel().getId().equals(travel.getId())) {
            throw new TravelBlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to travel");
        }

        //Update comment
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setComment(commentDTO.getComment());

        //Save comment
        Comment updatedComment = commentRepository.save(comment);
        return convertToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long travelId, Long commentId) {
        //Get travel by id
        Travel travel = travelRepository.findById(travelId).orElseThrow(
                () -> new ResourceNotFoundException("Travel", "id", travelId));
        //Get comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        //Check if comment belongs to travel
        if (!comment.getTravel().getId().equals(travel.getId())) {
            throw new TravelBlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to travel");
        }
        //Delete comment
        commentRepository.delete(comment);
    }

    private CommentDTO convertToDTO(Comment comment) {
        return mapper.map(comment, CommentDTO.class);
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        return mapper.map(commentDTO, Comment.class);
    }
}
