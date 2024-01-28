package dev.line4.blackBoard.filtering.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FilterBlackBoardDto {

    private BlackBoard blackboard;

    @Getter
    @NoArgsConstructor
    public static class BlackBoard {
        private String title;
        private String introduction;
        private String userId;
    }
}