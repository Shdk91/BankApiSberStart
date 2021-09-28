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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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
    public void GetAccountTestWithTrueLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getAccounts?login=kozlovda"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void GetAccountTestWithFalseLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getAccounts?login=kozlov"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void GetCardTestWithFalseLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getCards?login=kozlov"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void GetCardTestWithTrueLogin() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getCards?login=kozlovda"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void GetCardTestWithTrueLoginAndNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlovda&number=22222222222222222222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("0")));
    }

    @Test
    public void GetCardTestWithTrueLoginAndFalseNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlovda&number=2222222222222222222"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void GetCardTestWithFalseLoginAndFalseNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlova&number=2222222222222222222"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void GetCardTestWithFalseLoginAndTrueNumber() throws Exception {
        this.mockMvc
                .perform(get("/clientApi/getBalance?login=kozlova&number=22222222222222222222"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
