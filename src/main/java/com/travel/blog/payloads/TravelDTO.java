package com.travel.blog.payloads;

import lombok.Data;

@Data
public class TravelDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String date;
    private String location;
}
