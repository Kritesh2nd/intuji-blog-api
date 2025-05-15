package com.example.Blog.exception;

import com.example.Blog.service.BlogServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({NoSuchElementException.class,ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ProblemDetail> handleException(Exception ex){
        ProblemDetail problemDetail = null;

        if(ex instanceof NoSuchElementException){
            logger.error("Element not found error");
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
        }

        if(ex instanceof ConstraintViolationException){
            logger.error("Constraint violation error. Empty or blank space as value for variable.");
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Invalid value in Model");
        }

        if(ex instanceof MethodArgumentNotValidException){
            logger.error("Invalid input from user.");
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Invalid input in Request Body");
        }

        if(problemDetail == null){
            logger.error("Unknown error in server");
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,"Something wrong with server");
        }

        problemDetail.setProperty("message-type","error message");
        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }
}
