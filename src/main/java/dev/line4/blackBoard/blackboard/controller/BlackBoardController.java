package dev.line4.blackBoard.blackboard.controller;

import dev.line4.blackBoard.blackboard.dto.BlackBoardCountDto;
import dev.line4.blackBoard.blackboard.dto.BlackBoardOpenResDto;
import dev.line4.blackBoard.blackboard.dto.BlackBoardReqDto;
import dev.line4.blackBoard.blackboard.dto.BlackBoardResDto;
import dev.line4.blackBoard.blackboard.service.BlackBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "칠판")
public class BlackBoardController {
    @Autowired
    private final BlackBoardService blackBoardService;
    private final ModelMapper modelMapper;

    @Autowired
    public BlackBoardController(BlackBoardService blackBoardService, ModelMapper modelMapper) {
        this.blackBoardService = blackBoardService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/blackboards")
    @ApiOperation(value = "칠판 개수", notes = "현재까지 생성된 칠판의 개수를 리턴합니다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "{\n"
//                    + "    \"blackboardCount\": 232\n"
//                    + "}")
//    })
    public BlackBoardCountDto getTotalRecordCount() {
        return BlackBoardCountDto.builder()
                .blackboardCount(blackBoardService.getTotalRecordCount())
                .build();
    }

    @PostMapping("/blackboard")
    @ApiOperation(value = "칠판 생성", notes = "칠판을 생성할 때 호출합니다.")
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "{\n"
//                    + "    \"url\": \"c89f0fa5-607\"\n"
//                    + "}"),
//            @ApiResponse(code = 409, message = "{\n"
//                    + "    \"message\": 중복된 URL 입니다.\n"
//                    + "}")
//    })
    public ResponseEntity<BlackBoardResDto> createBlackBoard(@RequestBody BlackBoardReqDto blackBoardReqDto) {
        BlackBoardResDto responseDto = blackBoardService.createBlackBoard(blackBoardReqDto);

        if (responseDto.getUrl() == null) {
            // 이미 생성한 경우, 실패 응답
            return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
        }

        // 생성 성공 응답
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @GetMapping("/blackboard")
    @ApiOperation(value = "칠판과 편지", notes = "칠판을 조회할 때 호출합니다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "{\n"
//                    + "    \"url\": \"c89f0fa5-607\"\n"
//                    + "}"),
//            @ApiResponse(code = 404, message = "{\n}"
//                    + "    \"message\": \"존재하지 않는 URL 입니다.\"\n"
//                    + "}")
//    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "칠판 아이디", required = true, dataType = "string", paramType = "query", example = "c89f0fa5-607"),
    })
    public ResponseEntity<BlackBoardOpenResDto> getBlackBoardAndLetter(@RequestParam("id") String blackboardId) {
        BlackBoardOpenResDto blackBoardOpenResDto = blackBoardService.getBlackBoardAndLetter(blackboardId);

        BlackBoardOpenResDto responseDto = modelMapper.map(blackBoardOpenResDto, BlackBoardOpenResDto.class);
        return ResponseEntity.ok(responseDto);
    }
}
