package dev.line4.blackBoard.letter.service;

import dev.line4.blackBoard.letter.dto.CreateLetterDto;
import dev.line4.blackBoard.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface LetterService {
    ResponseEntity<ApiResponse<?>> createLetter(String userId, CreateLetterDto.Req req);
    ResponseEntity<ApiResponse<?>> readWriter(String userId);
}