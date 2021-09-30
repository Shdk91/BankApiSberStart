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
public class TranslationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TranslationWithFalseName() throws Exception {
        mockMvc
                .perform(post("/clientApi/translationMoney")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_COUNTERPARTY_FALSE_NAME))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TranslateWithFalseAccount() throws Exception {
        mockMvc
                .perform(post("/clientApi/translationMoney")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.TRANSLATION_WITH_FALSE_ACCOUNT))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TranslateWithFalseCounterparties() throws Exception {
        mockMvc
                .perform(post("/clientApi/translationMoney")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.TRANSLATION_WITH_FALSE_COUNTERPARTIES))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TranslateWithSmallBalance() throws Exception {
        mockMvc
                .perform(post("/clientApi/translationMoney")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.TRANSLATION_WITH_NORMAL_DATA))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TranslateWithNormalData() throws Exception {

        mockMvc
                .perform(post("/clientApi/addCash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_CASH_NORMAL))
                .andDo(print())
                .andExpect(status().isOk());

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                mockMvc
                        .perform(post("/clientApi/translationMoney")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestHelper.TRANSLATION_WITH_NORMAL_DATA))
                        .andDo(print())
                        .andExpect(status().isOk());
            } else {
                mockMvc
                        .perform(post("/clientApi/translationMoney")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestHelper.TRANSLATION_WITH_NORMAL_DATA))
                        .andDo(print())
                        .andExpect(status().is4xxClientError());
            }
        }

    }


}
