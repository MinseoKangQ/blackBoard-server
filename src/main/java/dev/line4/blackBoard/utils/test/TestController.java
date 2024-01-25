package dev.line4.blackBoard.utils.test;

import dev.line4.blackBoard.utils.response.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Validated
@RestController
@RequestMapping("api")
@Api(tags = "테스트")
public class TestController {

    @GetMapping("test-success")
    public ResponseEntity<?> testSuccess() {
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.createSuccessWithData("this is data", "test success"));
    }

    @GetMapping("test-not-found/{email}")
    public ResponseEntity<?> testNotFound(@PathVariable("email") @NotEmpty(message = "비어있을 수 없습니다.") @Email(message = "이메일 형식을 맞춰주세요.") String email) {
        char lowerCase = Character.toLowerCase(email.charAt(1));
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.createSuccessWithData("this is data", "조회 성공"));
    }
}
