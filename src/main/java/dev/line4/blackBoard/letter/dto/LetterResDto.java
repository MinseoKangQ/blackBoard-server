package dev.line4.blackBoard.letter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LetterResDto {
    @ApiModelProperty(notes = "칠판 아이디", example = "c89f0fa5-607")
    private String url;
}
