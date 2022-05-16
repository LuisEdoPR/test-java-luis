package com.challenge.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserException extends RuntimeException {

    private final String message;
    private final Throwable cause;
    private final int statusCode;

}
