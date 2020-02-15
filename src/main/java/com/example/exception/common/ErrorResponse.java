package com.example.exception.common;

import lombok.Data;
import lombok.Setter;

@Data
public class ErrorResponse {
    private String message;
    private String code;
}
