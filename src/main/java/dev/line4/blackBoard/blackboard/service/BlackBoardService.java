package dev.line4.blackBoard.blackboard.service;

//import dev.line4.blackBoard.blackboard.dto.BlackBoardReqDto;
//import dev.line4.blackBoard.blackboard.dto.BlackBoardResDto;
import dev.line4.blackBoard.blackboard.dto.CreateBlackBoardDto;
import dev.line4.blackBoard.blackboardsticker.dto.BlackBoardStickerResDto;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import dev.line4.blackBoard.letter.dto.LetterOpenResDto;
import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.lettersticker.dto.LetterStickerReqDto;
import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import dev.line4.blackBoard.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface BlackBoardService {

    // 생성된 칠판의 개수 가져오기
    ResponseEntity<ApiResponse<?>> getBlackBoardCount();

    // 칠판 생성하기
    ResponseEntity<ApiResponse<?>> createBlackBoard(CreateBlackBoardDto.Req req) throws Exception;

    // 칠판, 편지 조회
    ResponseEntity<ApiResponse<?>> getBlackBoardAndLetter(String userId);

    // 아이디 중복 확인
    ResponseEntity<ApiResponse<?>> checkDuplicateUserId(String userId);



}
