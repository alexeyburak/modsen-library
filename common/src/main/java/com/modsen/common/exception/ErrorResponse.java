package com.modsen.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}
