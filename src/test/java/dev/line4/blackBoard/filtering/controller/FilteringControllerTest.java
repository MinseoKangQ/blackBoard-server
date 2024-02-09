package dev.line4.blackBoard.filtering.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static dev.line4.blackBoard.JsonString.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FilteringControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @DisplayName("칠판 비속어 필터링 - 200")
    void filterBlackBoard_200() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/blackboard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(BLACKBOARD_SUCCESS_CONTENT))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("칠판 비속어 필터링 - 400")
    void filterBlackBoard_400() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_FAIL_CONTENT))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    @DisplayName("편지 비속어 필터링 - 200")
    void filterLetter_200() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LETTER_SUCCESS_CONTENT))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("편지 비속어 필터링 - 400")
    void filterLetter_400() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LETTER_FAIL_CONTENT))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}