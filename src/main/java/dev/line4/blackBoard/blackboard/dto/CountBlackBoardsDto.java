package dev.line4.blackBoard.blackboard.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class CountBlackBoardsDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {

        @ApiModelProperty(notes = "칠판 개수", example = "8")
        private Long blackboardCount;

        @Builder
        public Res(Long blackboardCount) {
            this.blackboardCount = blackboardCount;
        }
    }

}
