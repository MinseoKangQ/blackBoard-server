package dev.line4.blackBoard.filtering.service;

import dev.line4.blackBoard.filtering.dto.FilterBlackBoardDto;
import dev.line4.blackBoard.filtering.dto.FilterLetterDto;
import dev.line4.blackBoard.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface FilteringService {

    ResponseEntity<ApiResponse<?>> filterBlackBoard(FilterBlackBoardDto.Req req) throws Exception;

    ResponseEntity<ApiResponse<?>> filterLetter(FilterLetterDto.Req req) throws Exception;

}
