package dev.line4.blackBoard.letter.service;

import dev.line4.blackBoard.blackboard.dto.ReadBlackBoardAndLettersDto;
import dev.line4.blackBoard.letter.dto.AddLetterDto;
import dev.line4.blackBoard.letter.entity.LetterEntity;
import dev.line4.blackBoard.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LetterService {
    ResponseEntity<ApiResponse<?>> addLetter(String userId, AddLetterDto.Req req);
    ResponseEntity<ApiResponse<?>> getLetterWriters(String userId);
    List<ReadBlackBoardAndLettersDto.Res.Letter> convertToLetterAndLetterStickerDtoList(List<LetterEntity> letters);
}