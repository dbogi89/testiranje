package com.htec.api.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final LocalDateTime timestamp;

    private final int status;

    private final int code;

    private String error;

    private String description;

    public static ErrorResponse of(HttpStatus status, int code, String error, String description) {
        return new ErrorResponse(LocalDateTime.now(), status.value(), code, error, description);
    }
}
