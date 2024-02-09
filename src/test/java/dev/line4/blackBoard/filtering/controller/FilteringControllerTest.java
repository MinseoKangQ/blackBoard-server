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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FilteringControllerTest {

    @Autowired
    MockMvc mvc;

    String blackboardSContent = "{"
            + "\"blackboard\" : {"
            + "\"title\" : \"칠판 안녕\","
            + "\"introduction\" : \"칠판 소개\","
            + "\"userId\" : \"takaakkad\""
            + "}"
            + "}";

    String blackboardFContent = "{"
            + "\"blackboard\" : {"
            + "\"title\" : \"칠판 시발\","
            + "\"introduction\" : \"칠판 소개\","
            + "\"userId\" : \"takaakkad\""
            + "}"
            + "}";

    String letterSContent = "{"
            + "\"letter\" : {"
            + "\"nickname\":\"편지 작성자\","
            + "\"content\":\"이건 텍스트 에\""
            + "}"
            + "}";

    String letterFContent = "{"
            + "\"letter\" : {"
            + "\"nickname\":\"편지 작성자\","
            + "\"content\":\"병신아\""
            + "}"
            + "}";

    @Test
    @Transactional
    @DisplayName("칠판 비속어 필터링 - 성공")
    void filterBlackBoardSuccess() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/blackboard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(blackboardSContent))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("칠판 비속어 필터링 - 실패")
    void filterBlackBoardFail() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(blackboardFContent))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    @DisplayName("편지 비속어 필터링 - 성공")
    void filterLetterSuccess() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(letterSContent))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("편지 비속어 필터링 - 실패")
    void filterLetterFail() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/api/filtering/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(letterFContent))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }



}