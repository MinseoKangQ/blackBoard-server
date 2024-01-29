package dev.line4.blackBoard.blackboard.service;

import dev.line4.blackBoard.blackboard.dto.AddBlackBoardDto;
import dev.line4.blackBoard.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface BlackBoardService {

    // 생성된 칠판의 개수 가져오기
    ResponseEntity<ApiResponse<?>> countBlackBoards();

    // 칠판 생성하기
    ResponseEntity<ApiResponse<?>> addBlackBoard(AddBlackBoardDto.Req req) throws Exception;

    // 칠판, 편지 조회
    ResponseEntity<ApiResponse<?>> readBlackBoardAndLetters(String userId);

    // 아이디 중복 확인
    ResponseEntity<ApiResponse<?>> checkDuplicateUserId(String userId);

}
