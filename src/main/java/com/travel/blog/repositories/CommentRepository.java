package com.travel.blog.repositories;
import com.travel.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //Get all comments by travel id
    List<Comment> findAllByTravelId(Long travelId);
}
