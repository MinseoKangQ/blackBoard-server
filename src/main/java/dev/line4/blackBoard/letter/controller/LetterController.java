package dev.line4.blackBoard.letter.controller;

import dev.line4.blackBoard.letter.dto.CreateLetterDto;
import dev.line4.blackBoard.letter.service.LetterServiceImpl;
import dev.line4.blackBoard.utils.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Api(tags = "편지")
public class LetterController {

    private final LetterServiceImpl letterService;

    @PostMapping("letter")
    @ApiOperation(value = "편지 생성", notes = "편지를 생성할 때 호출합니다.")
    public ResponseEntity<ApiResponse<?>> createLetter(@RequestBody CreateLetterDto.Req req,
                                                    @RequestParam("userId") String userId) {
        ResponseEntity<ApiResponse<?>> result = letterService.createLetter(userId, req);
        return result;
    }

    @GetMapping("writer")
    @ApiOperation(value = "편지 작성자 조회", notes = "칠판에 편지를 작성한 사람들을 조회할 때 호출합니다.")
    public ResponseEntity<ApiResponse<?>> readWriter(@RequestParam("userId") String userId) {
        ResponseEntity<ApiResponse<?>> result = letterService.readWriter(userId);
        return result;
    }

}
