package dev.line4.blackBoard.utils.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @DisplayName("존재하지 않는 API 요청 - 404")
    void notFound_404() throws Exception{

        mvc.perform(MockMvcRequestBuilders.post("/non-exist-path"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}