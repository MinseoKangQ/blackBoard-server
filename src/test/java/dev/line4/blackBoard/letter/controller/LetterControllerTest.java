package dev.line4.blackBoard.letter.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LetterControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @DisplayName("편지 작성자 조회 0명 - 200")
    void getLetterWriters0_200() throws Exception {

        // 칠판 등록
        registerBlackboard();

        // 응답 확인 - 편지 등록자가 0명이어야 함
        mvc.perform(MockMvcRequestBuilders.get("/api/writer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "this-is-test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.writers.length()").value(0));
    }

    @Test
    @Transactional
    @DisplayName("편지 작성자 조회 2명 - 200")
    void getLetterWriters2_200() throws Exception {

        // 칠판 등록
        registerBlackboard();

        // 편지 등록 1 - test1
        registerLetter(LETTER_1);

        // 편지 등록 2 - test2
        registerLetter(LETTER_2);

        // 응답 확인 - 편지 등록자가 2명이어야 함
        mvc.perform(MockMvcRequestBuilders.get("/api/writer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "this-is-test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.writers.length()").value(2));
    }

    @Test
    @DisplayName("편지 작성자 조회 - 404")
    @Transactional
    void getLetterWriters_404() throws Exception {

        // 존재하지 않는 칠판으로 요청을 보낸 경우 404
        mvc.perform(MockMvcRequestBuilders.get("/api/writer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "never-existssssss"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("편지 작성 - 200")
    @Transactional
    void addLetter_200() throws Exception {

        // 칠판 등록
        registerBlackboard();

        // 편지 등록
        mvc.perform(MockMvcRequestBuilders.post("/api/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "this-is-test")
                        .content(LETTER_1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("편지 작성 - 404")
    @Transactional
    void addLetter_404() throws Exception {

        // 편지 등록 - 등록되지 않은 칠판에 편지를 작성하므로 404
        mvc.perform(MockMvcRequestBuilders.post("/api/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "tdkfjekwjkjeksjaljsdk")
                        .content(LETTER_1))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // 공통된 테스트 작업을 위한 메소드
    private void registerBlackboard() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private void registerLetter(String letter) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "this-is-test")
                        .content(letter))
                .andDo(print())
                .andExpect(status().isOk());
    }

}