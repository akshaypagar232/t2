package com.payment.exception;

import org.springframework.http.HttpStatus;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseMessage {

    private String message;

    private boolean status;

    private HttpStatus success;

}
