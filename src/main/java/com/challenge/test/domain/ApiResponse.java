package com.challenge.test.domain;

import lombok.*;

import java.net.URI;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {
    URI instance;
    String title;
    int status;
    String detail;
}
