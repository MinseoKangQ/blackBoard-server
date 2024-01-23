package dev.line4.blackBoard.letter.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReadWriterDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private List<String> writers;

        @Builder
        public Res(List<String> writers) {
            this.writers = writers;
        }
    }

}
