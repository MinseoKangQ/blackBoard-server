package dev.line4.blackBoard.utils.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Api(tags = "테스트")
public class TestController {

    @GetMapping("test")
    @ApiOperation(value = "테스트", notes = "프론트와 통신 시 테스트 목적으로 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "테스트 성공입니다.")
    })
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.OK).body("테스트 성공입니다.");
    }
}
