package dev.line4.blackBoard.filtering.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FilterBlackBoardDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {
        private BlackBoard blackboard;

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class BlackBoard {
            private String title;
            private String introduction;
            private String userId;
        }
    }
}