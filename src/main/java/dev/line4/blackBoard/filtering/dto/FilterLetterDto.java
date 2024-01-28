package dev.line4.blackBoard.filtering.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FilterLetterDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {
        private Letter letter;

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Letter {
            private String nickname;
            private String content;
        }
    }
}
