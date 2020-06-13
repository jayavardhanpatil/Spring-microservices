package io.spring.movieinfoservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieIdNotFoundException extends RuntimeException {

    public MovieIdNotFoundException() {
    }

    public MovieIdNotFoundException(String message) {
        super(message);
    }
}
