package com.travel.blog.payloads;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String comment;
    private String email;
    private String name;
}
