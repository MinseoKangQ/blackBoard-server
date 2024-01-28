package dev.line4.blackBoard.filtering.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FilterLetterDto {

    private Letter letter;

    @Getter
    @NoArgsConstructor
    public static class Letter {
        private String nickname;
        private String content;
    }
}
