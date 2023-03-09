package com.travel.blog.exceptions;

import org.springframework.http.HttpStatus;

//Exception to validate request parameters
public class TravelBlogAPIException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public TravelBlogAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public TravelBlogAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
