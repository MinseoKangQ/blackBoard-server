package dev.line4.blackBoard.blackboard.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReadBlackBoardAndLettersDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {

        private BlackBoard blackboard;
        private List<Letter> letter;

        @Builder
        public Res(BlackBoard blackboard, List<Letter> letter) {
            this.blackboard = blackboard;
            this.letter = letter;
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class BlackBoard {
            private String title;
            private String introduction;
            private String userId;
            private String openDate;
            private List<Sticker> stickers;

            @Builder
            public BlackBoard(String title, String introduction, String userId, String openDate, List<Sticker> stickers) {
                this.title = title;
                this.introduction = introduction;
                this.userId = userId;
                this.openDate = openDate;
                this.stickers = stickers;
            }
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Letter {
            private Long id;
            private String nickname;
            private String content;
            private String font;
            private String align;
            private List<Sticker> stickers;

            @Builder
            public Letter(Long id, String nickname, String content, String font, String align, List<Sticker> stickers) {
                this.id = id;
                this.nickname = nickname;
                this.content = content;
                this.font = font;
                this.align = align;
                this.stickers = stickers;
            }
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

        @Builder
        public Sticker(Long num, Double positionX, Double positionY, Long img, Double width, Double angle, Long mirror) {
            this.num = num;
            this.positionX = positionX;
            this.positionY = positionY;
            this.img = img;
            this.width = width;
            this.angle = angle;
            this.mirror = mirror;
        }
    }
}
