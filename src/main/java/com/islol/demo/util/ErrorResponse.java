package com.islol.demo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String msg;

    public ErrorResponse(HttpStatus status, String msg) {
        this.status = status.value();
        this.msg = msg;
    }
}
