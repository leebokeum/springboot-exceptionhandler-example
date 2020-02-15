package com.example.exception.accounts;

import lombok.Getter;

@Getter
public class UserDataException extends RuntimeException{

    private String userName;

    public UserDataException(String userName){
        this.userName = userName;
    }
}
