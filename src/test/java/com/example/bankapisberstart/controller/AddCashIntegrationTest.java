package com.example.bankapisberstart.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AddCashIntegrationTest {

    @Autowired
    private ClientApiController clientApiController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void addCashFalseName() throws Exception {
        mockMvc
                .perform(post("/clientApi/addCash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_CASH_FALSE_NAME))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCashWithFalseNumber() throws Exception {
        mockMvc
                .perform(post("/clientApi/addCash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_CASH_FALSE_NUMBER))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCashWithEmptyBody() throws Exception {
        mockMvc
                .perform(post("/clientApi/addCash")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
