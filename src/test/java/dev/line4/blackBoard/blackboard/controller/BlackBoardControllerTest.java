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

    @Test
    @DisplayName("칠판 등록 - 실패")
    @Transactional // 테스트 완료 후 rollback
    void addBlackBoardTestFail() throws Exception {

        // JSON 데이터 문자열
        String jsonContent = "{"
                + "\"blackboard\" : {"
                + "    \"title\" : \"제목제목\","
                + "    \"introduction\" : \"소개소개\","
                + "    \"userId\" : \"duplicateUserTest\","
                + "    \"openDate\" : \"2024-02-20T18:00:00\""
                + "},"
                + "\"stickers\" : ["
                + "]"
                + "}";

        // 첫 번째 요청은 성공
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)) // JSON 데이터 요청 본문에 포함
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());


        // 두 번째 요청은 실패
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)) // JSON 데이터 요청 본문에 포함
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("칠판 id 중복 검증 - 성공1")
    @Transactional
    void checkDuplicateUserIdTestSuccess1() throws Exception{

        String userId1 = "thisIsDuplicateTestId1";
        String userId2 = "thisIsDuplicateTestId2";

        // 첫 번째 요청 성공
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", userId1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 두 번째 요청 성공
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", userId2))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("칠판 id 중복 검증 - 성공2")
    @Transactional
    void checkDuplicateUserIdTestSuccess2() throws Exception{

        String userId = "thisIsDuplicateTestId";

        // JSON 데이터 문자열
        String jsonContent = "{"
                + "\"blackboard\" : {"
                + "    \"title\" : \"제목제목\","
                + "    \"introduction\" : \"소개소개\","
                + "    \"userId\" : "
                + "\"thisIsDuplicateTestId\","
                + "    \"openDate\" : \"2024-02-20T18:00:00\""
                + "},"
                + "\"stickers\" : ["
                + "]"
                + "}";


        // 첫 번째 요청 성공
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", userId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 칠판 만들기
        mvc.perform(MockMvcRequestBuilders.post("/api/blackboard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)) // JSON 데이터 요청 본문에 포함
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 두 번째 요청 실패
        mvc.perform(MockMvcRequestBuilders.get("/api/check-duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", userId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}