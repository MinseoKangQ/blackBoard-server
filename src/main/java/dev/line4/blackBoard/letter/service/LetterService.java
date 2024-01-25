package dev.line4.blackBoard.letter.service;

import dev.line4.blackBoard.blackboard.dto.GetBlackBoardAndLetterDto;
import dev.line4.blackBoard.letter.dto.CreateLetterDto;
import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LetterService {
    ResponseEntity<ApiResponse<?>> createLetter(String userId, CreateLetterDto.Req req);
    ResponseEntity<ApiResponse<?>> readWriter(String userId);
    List<GetBlackBoardAndLetterDto.Res.Letter> convertToLetterAndLetterStickerDtoList(List<Letters> letters);
}