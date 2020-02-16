package com.example.exception.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void createAccount() throws Exception {
        Account account = new Account();
        account.setUserId("12345");
        account.setPassword("123");
        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions result = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content (objectMapper.writeValueAsString(account))
        );

        result.andDo(print());
    }

    @Test
    public void createAccountBadRequest() throws Exception {
        ResultActions result = mockMvc.perform(get("/accountsException")
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andDo(print());
    }


}