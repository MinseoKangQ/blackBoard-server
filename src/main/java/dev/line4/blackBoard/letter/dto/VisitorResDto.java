package dev.line4.blackBoard.letter.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorResDto {
    @ApiModelProperty(notes = "편지 작성자", example = "[\n"
            + "        \"배포닉네임\", \"멋쟁이사자\", \"8팀짱\"\n"
            + "]")
    private List<String> nickname;
}
