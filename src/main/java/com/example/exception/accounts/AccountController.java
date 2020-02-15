package com.example.exception.accounts;

import com.example.exception.common.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    //정상
    @GetMapping("/accounts")
    public ResponseEntity createAccount(){
        Account account = new Account();
        account.setUserId("userId");
        account.setPassword("password");

        //Exception 발생
        if(false){
            System.out.println(account.getUserId() + " Exception 발생");
            throw new UserDataException(account.getUserId());
        }

        return new ResponseEntity<>(new AccountDto(account.getUserId()), HttpStatus.OK);
    }

    //예외발생
    @GetMapping("/accountsException")
    public ResponseEntity createAccountForException(){
        Account account = new Account();
        account.setUserId("userId");
        account.setPassword("password");

        //Exception 발생
        if(true){
            System.out.println(account.getUserId() + " Exception 발생");
            throw new UserDataException(account.getUserId());
        }

        return new ResponseEntity<>(new AccountDto(account.getUserId()), HttpStatus.OK);
    }

    //UserDataException ExceptionHandler
    @ExceptionHandler(UserDataException.class)
    public ResponseEntity handleUserDataException(UserDataException e){
        System.out.println("handleUserDataException 실행");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getUserName() + "유저명은 잘못되었습니다.");
        errorResponse.setCode("UserDataException");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
