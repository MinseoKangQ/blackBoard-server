package dev.line4.blackBoard.blackboard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static dev.line4.blackBoard.JsonString.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class BlackBoardControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("칠판 개수 조회 - 200")
    void countBlackBoards_200 () throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/blackboards").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.blackboardCount", is(0)));
    }

    @Test
    @DisplayName("칠판 등록 - 200")
    @Transactional
    void addBlackBoard_200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_CONTENT))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("칠판 등록 - 400")
    @Transactional
    void addBlackBoard_400() throws Exception {

        // 첫 번째 요청은 성공
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_DUPLICATE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());


        // 두 번째 요청은 실패
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_DUPLICATE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("칠판 id 중복 검증 - 200")
    @Transactional
    void checkDuplicateUserId_200() throws Exception{

        // thisIsDuplicateTestId1 검증 성공
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "thisIsDuplicateTestId1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 칠판 생성 성공
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_DUPLICATE_ID_1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());


        // thisIsDuplicateTestId2 검증 성공
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "thisIsDuplicateTestId2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 칠판 생성 성공
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_DUPLICATE_ID_2))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("칠판 id 중복 검증 - 400")
    @Transactional
    void checkDuplicateUserId_400() throws Exception{

        // 첫 번째 요청 성공
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "thisIsDuplicateTestId"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 칠판 만들기 성공
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_DUPLICATE_BAD))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 두 번째 요청 실패
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "thisIsDuplicateTestId"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // 칠판 만들기 실패
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BLACKBOARD_DUPLICATE_BAD))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("칠판과 편지 조회 - 200")
    @Transactional
    void readBlackBoardAndLetters_200() throws Exception {

        // 칠판 만들기 성공
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(READ_BLACKBOARD_AND_LETTER))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 편지 등록 성공
        mvc.perform(MockMvcRequestBuilders.post("/api/letter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "test1")
                        .content(LETTER))
                .andDo(print())
                .andExpect(status().isOk());

        // 응답 확인
        mvc.perform(MockMvcRequestBuilders.get("/api/blackboard")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "test1"))
                .andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("칠판과 편지 조회 - 404")
    @Transactional
    void readBlackBoardAndLetters_404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "test1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}