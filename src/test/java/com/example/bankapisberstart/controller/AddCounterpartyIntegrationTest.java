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
public class AddCounterpartyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void addCounterpartyWithNormalDataDuplicate() throws Exception {
        this.mockMvc
                .perform(post("/clientApi/addCounterparty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_COUNTERPARTY_NORMAL))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc
                .perform(post("/clientApi/addCounterparty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_COUNTERPARTY_NORMAL))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCounterpartyWithFalseName() throws Exception {
        this.mockMvc
                .perform(post("/clientApi/addCounterparty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_COUNTERPARTY_FALSE_NAME))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCounterpartyWithNotValidTaxNumber() throws Exception {
        this.mockMvc
                .perform(post("/clientApi/addCounterparty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.ADD_COUNTERPARTY_NOT_VALID_TAXNUMBER))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}
