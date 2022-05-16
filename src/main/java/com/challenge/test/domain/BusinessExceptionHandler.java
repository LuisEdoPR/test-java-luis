package com.challenge.test.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@ControllerAdvice
public class BusinessExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse> userNotFoundHandler(
            UserException userException,
            HttpServletRequest request
    ) {

        logger.error("user exception generated when calling " + request.getRequestURI(), userException);

        return responseEntityBuilder(
                userException.getStatusCode(),
                userException.getMessage(),
                userException.getCause(),
                URI.create(request.getRequestURI())
        );
    }

    private ResponseEntity<ApiResponse> responseEntityBuilder(
            int status,
            String title,
            Throwable detail,
            URI instance
    ) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(
                        ApiResponse.builder()
                                .instance(instance)
                                .title(title)
                                .status(status)
                                .detail(detail == null ? "" : detail.getMessage())
                                .build()
                );
    }

}
