package dev.line4.blackBoard.letter.dto;

import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CreateLetterDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {

        private Letter letter;
        private List<Sticker> stickers;

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Letter {
            private String nickname;
            private String content;
            private String font;
            private String align;

            public Letters toEntity() {
                return Letters.builder()
                        .nickname(this.nickname)
                        .content(this.content)
                        .font(this.font)
                        .align(this.align)
                        .build();
            }

            @Builder
            public Letter(String nickname, String content, String font, String align) {
                this.nickname = nickname;
                this.content = content;
                this.font = font;
                this.align = align;
            }
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Sticker {
            private Long num;
            private Double positionX;
            private Double positionY;
            private Long img;
            private Double width;
            private Double angle;
            private Long mirror;

            public LetterStickers toEntity() {
                return LetterStickers.builder()
                        .num(this.num)
                        .positionX(this.positionX)
                        .positionY(this.positionY)
                        .img(this.img)
                        .width(this.width)
                        .angle(this.angle)
                        .mirror(this.mirror)
                        .build();
            }
        }
    }
}
