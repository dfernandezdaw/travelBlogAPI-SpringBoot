package com.travel.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;
    // User has to enter email and name in the form to create a comment
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;

    // Relationship whit Travel. One comment belongs to one travel. Many comments to one travel
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id", nullable = false)
    private Travel travel;

   // Relationship with User model. One comment can be made by one user. Many comments to one user
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String comment, String email, String name, Travel travel, User user) {
        this.comment = comment;
        this.email = email;
        this.name = name;
        this.travel = travel;
        this.user = user;
    }
}
