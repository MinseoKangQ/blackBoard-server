package dev.line4.blackBoard.letter.controller;

import dev.line4.blackBoard.letter.dto.LetterReqDto;
import dev.line4.blackBoard.letter.dto.LetterResDto;
import dev.line4.blackBoard.letter.dto.VisitorResDto;
import dev.line4.blackBoard.letter.service.LetterServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Api(tags = "편지")
public class LetterController {

    private final LetterServiceImpl letterService;

    @PostMapping("letter")
    @ApiOperation(value = "편지 생성", notes = "편지를 생성할 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{\n"
                    + "    \"url\": \"c89f0fa5-607\"\n"
                    + "}"),
            @ApiResponse(code = 404, message = "{\n}"
                    + "    \"message\": \"존재하지 않는 URL 입니다.\"\n"
                    + "}")
    })
    public ResponseEntity<?> createLetter(@RequestBody LetterReqDto dto,
                                          @RequestParam("id") String blackboardId) {
        LetterResDto result = letterService.createLetter(dto, blackboardId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("visitor/{id}")
    @ApiOperation(value = "방문자 조회", notes = "칠판에 방문한 사람들을 조회할 때 호출합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "칠판 아이디", required = true, dataType = "string", paramType = "path", example = "c89f0fa5-607"),
    })
    public ResponseEntity<?> readVisitor(@RequestParam("id") String blackboardId) {
        VisitorResDto result = letterService.readVisitor(blackboardId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
