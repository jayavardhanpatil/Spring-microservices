package io.spring.movieinfoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizeResposneEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handlerException(Exception e, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
