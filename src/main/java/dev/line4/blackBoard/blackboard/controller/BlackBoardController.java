package dev.line4.blackBoard.blackboard.controller;

import dev.line4.blackBoard.blackboard.dto.AddBlackBoardDto;
import dev.line4.blackBoard.blackboard.service.BlackBoardServiceImpl;
import dev.line4.blackBoard.utils.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@Api(tags = "칠판")
public class BlackBoardController {

    private final BlackBoardServiceImpl blackBoardServiceImpl;

    // 완료
    @GetMapping("blackboards")
    @ApiOperation(value = "칠판 개수", notes = "현재까지 생성된 칠판의 개수를 리턴합니다.")
    public ResponseEntity<ApiResponse<?>> countBlackBoards() {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.countBlackBoards();
        return result;
    }

    // 완료
    @PostMapping("blackboard")
    @ApiOperation(value = "칠판 생성", notes = "칠판을 생성할 때 호출합니다.")
    public ResponseEntity<ApiResponse<?>> addBlackBoard(@RequestBody AddBlackBoardDto.Req req) {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.addBlackBoard(req);
        return result;
    }

    // 완료
    @GetMapping("blackboard")
    @ApiOperation(value = "칠판과 편지", notes = "칠판을 조회할 때 호출합니다.")
    public ResponseEntity<ApiResponse<?>> readBlackBoardAndLetters(@RequestParam("userId") String userId) {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.readBlackBoardAndLetters(userId);
        return result;
    }

    // 완료
    @GetMapping("check-duplicate")
    @ApiOperation(value = "userId 중복 확인", notes = "userId를 입력받은 후 호출합니다.")
    public ResponseEntity<ApiResponse<?>> checkDuplicateUserId(@RequestParam("userId") String userId) {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.checkDuplicateUserId(userId);
        return result;
    }

}
