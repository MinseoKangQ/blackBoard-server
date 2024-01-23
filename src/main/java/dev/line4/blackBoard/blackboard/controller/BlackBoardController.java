package dev.line4.blackBoard.blackboard.controller;

import dev.line4.blackBoard.blackboard.dto.CreateBlackBoardDto;
import dev.line4.blackBoard.blackboard.service.BlackBoardServiceImpl;
import dev.line4.blackBoard.utils.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Api(tags = "칠판")
public class BlackBoardController {

    private final BlackBoardServiceImpl blackBoardServiceImpl;

    // 완료
    @GetMapping("blackboards")
    @ApiOperation(value = "칠판 개수", notes = "현재까지 생성된 칠판의 개수를 리턴합니다.")
    public ResponseEntity<ApiResponse<?>> getBlackBoardCount() {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.getBlackBoardCount();
        return result;
    }

    // 완료
    @PostMapping("blackboard")
    @ApiOperation(value = "칠판 생성", notes = "칠판을 생성할 때 호출합니다.")
    public ResponseEntity<ApiResponse<?>> createBlackBoard(@RequestBody CreateBlackBoardDto.Req req) {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.createBlackBoard(req);
        return result;
    }

    @GetMapping("checkDuplicate")
    @ApiOperation(value = "userId 중복 확인", notes="userId를 입력받은 후 호출합니다.")
    public ResponseEntity<ApiResponse<?>> checkDuplicateUserId(@RequestParam("userId") String userId ) {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.checkDuplicateUserId(userId);
        return result;
    }

    // TODO: 리팩토링
    /*
    @GetMapping("/blackboard")
    @ApiOperation(value = "칠판과 편지", notes = "칠판을 조회할 때 호출합니다.")
    public ResponseEntity<BlackBoardOpenResDto> getBlackBoardAndLetter(@RequestParam("id") String blackboardId) {
            BlackBoardOpenResDto blackBoardOpenResDto = blackBoardServiceImpl.getBlackBoardAndLetter(blackboardId);
            BlackBoardOpenResDto responseDto = modelMapper.map(blackBoardOpenResDto, BlackBoardOpenResDto.class);
            return ResponseEntity.ok(responseDto);
    }
     */

}
