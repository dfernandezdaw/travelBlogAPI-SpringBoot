package com.travel.blog.payloads;

import lombok.Data;

import java.util.Date;

@Data
public class TravelDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Date date;
    private String location;
}
