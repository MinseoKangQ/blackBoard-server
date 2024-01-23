package dev.line4.blackBoard.blackboard.controller;

import dev.line4.blackBoard.blackboard.service.BlackBoardServiceImpl;
import dev.line4.blackBoard.utils.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<ApiResponse<?>> getBlackBoardCount() {
        ResponseEntity<ApiResponse<?>> result = blackBoardServiceImpl.getBlackBoardCount();
        return result;
    }

    // TODO: 리팩토링
    /*@PostMapping("/blackboard")
    @ApiOperation(value = "칠판 생성", notes = "칠판을 생성할 때 호출합니다.")
    public ResponseEntity<BlackBoardResDto> createBlackBoard(@RequestBody BlackBoardReqDto blackBoardReqDto) {
        BlackBoardResDto responseDto = blackBoardServiceImpl.createBlackBoard(blackBoardReqDto);

        if (responseDto.getUrl() == null) {
            // 이미 생성한 경우, 실패 응답
            return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
        }

        // 생성 성공 응답
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }
    */


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
