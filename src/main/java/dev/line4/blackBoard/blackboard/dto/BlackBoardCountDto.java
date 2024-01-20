package dev.line4.blackBoard.blackboard.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlackBoardCountDto {
    @ApiModelProperty(notes = "칠판 개수", example = "8")
    private Long blackboardCount;
}
