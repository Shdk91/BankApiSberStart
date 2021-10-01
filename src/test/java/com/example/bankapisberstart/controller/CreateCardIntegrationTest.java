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
public class CreateCardIntegrationTest {

    @Autowired
    private ClientApiController clientApiController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCardWithNormalData() throws Exception {
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                mockMvc
                        .perform(post("/clientApi/createCard")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestHelper.CREATE_CARD_NORMAL))
                        .andDo(print())
                        .andExpect(status().isOk());
            } else {
                mockMvc
                        .perform(post("/clientApi/createCard")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestHelper.CREATE_CARD_NORMAL))
                        .andDo(print())
                        .andExpect(status().is4xxClientError());
            }
        }
    }

    @Test
    public void createCardWithFalseName() throws Exception {
        mockMvc
                .perform(post("/clientApi/createCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CREATE_CARD_FALSE_NAME))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createCardWithFalseNumber() throws Exception {
        mockMvc
                .perform(post("/clientApi/createCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CREATE_CARD_FALSE_NUMBER))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createCardWithEmptyBody() throws Exception {
        mockMvc
                .perform(post("/clientApi/createCard")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }



}
