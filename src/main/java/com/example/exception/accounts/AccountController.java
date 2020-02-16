package com.example.exception.accounts;

import com.example.exception.common.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

    //바인딩 객체의 validation 오류일 때 exception 처리
    @PostMapping("/accounts")
    public ResponseEntity createAccount( @Valid @RequestBody Account account, BindingResult bindingResult){

        // 예외처리 -> bindingResult.getFieldError().getDefaultMessage() 를 통하여 리턴받을 수 있음
        if(bindingResult.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse();
            StringBuilder builder = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                builder.append("[");
                builder.append(fieldError.getField());
                builder.append("](은)는 ");
                builder.append(fieldError.getDefaultMessage());
                builder.append(" 입력된 값: [");
                builder.append(fieldError.getRejectedValue());
                builder.append("]");
            }

            errorResponse.setMessage(builder.toString());
            errorResponse.setCode("BindingResultException");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new AccountDto(account.getUserId()), HttpStatus.OK);
    }

    //임의 예외발생
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
