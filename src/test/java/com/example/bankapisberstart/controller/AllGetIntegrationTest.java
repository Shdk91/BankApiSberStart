package com.example.bankapisberstart.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AllGetIntegrationTest {

    @Autowired
    private ClientApiController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAccountTestWithTrueLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getAccounts?login=kozlovda"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAccountTestWithFalseLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getAccounts?login=kozlov"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCardTestWithFalseLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getCards?login=kozlov"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCardTestWithTrueLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getCards?login=kozlovda"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCardTestWithTrueLoginAndNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlovda&number=22222222222222222222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("0")));
    }

    @Test
    public void getCardTestWithTrueLoginAndFalseNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlovda&number=2222222222222222222"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCardTestWithFalseLoginAndFalseNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlova&number=2222222222222222222"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCardTestWithFalseLoginAndTrueNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlova&number=22222222222222222222"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCounterpartiesWithCorrectLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getCounterparties?login=kozlovda"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCounterpartiesWithFalseLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getCounterparties?login=kozlovd"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
