package com.example.exception.accounts;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Account {
    @Size(max = 10)
    @NotBlank
    String userId;
    @NotBlank
    String password;

}
