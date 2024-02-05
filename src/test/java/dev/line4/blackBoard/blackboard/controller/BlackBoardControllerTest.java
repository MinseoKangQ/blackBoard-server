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

@SpringBootTest
@AutoConfigureMockMvc
public class BlackBoardControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("칠판 개수 조회 - 성공")
    void countBlackBoardsTestSuccess () throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/blackboards").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("칠판 등록 - 성공")
    @Transactional // 테스트 완료 후 rollback
    void addBlackBoardTestSuccess() throws Exception {

        // JSON 데이터 문자열
        String jsonContent = "{"
                + "\"blackboard\" : {"
                + "    \"title\" : \"칠판 안녕\","
                + "    \"introduction\" : \"칠판 소개\","
                + "    \"userId\" : \"testttttttttttttttttttttt\","
                + "    \"openDate\" : \"2024-02-20T18:00:00\""
                + "},"
                + "\"stickers\" : ["
                + "    {"
                + "        \"num\" : 1,"
                + "        \"positionX\" : 0.1,"
                + "        \"positionY\" : 0.2,"
                + "        \"img\" : 3,"
                + "        \"width\" : 1.5,"
                + "        \"angle\" : 0.5,"
                + "        \"mirror\" : -1"
                + "    },"
                + "    {"
                + "        \"num\" : 2,"
                + "        \"positionX\" : 0.3,"
                + "        \"positionY\" : 0.4,"
                + "        \"img\" : 7,"
                + "        \"width\" : 1.2,"
                + "        \"angle\" : 0.7,"
                + "        \"mirror\" : 1"
                + "    }"
                + "]"
                + "}";

        // POST 요청 구성 및 실행
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)) // JSON 데이터 요청 본문에 포함
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}