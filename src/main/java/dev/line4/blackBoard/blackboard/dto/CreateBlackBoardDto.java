package dev.line4.blackBoard.blackboard.dto;

import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CreateBlackBoardDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Req {

        private BlackBoard blackboard;
        private List<Sticker> stickers;

        @Getter
        @NoArgsConstructor(access = AccessLevel.PACKAGE)
        public static class BlackBoard {
            private String title;
            private String introduction;
            private String userId;
            private String graduateDate;

            public BlackBoards toEntity() {
                return BlackBoards.builder()
                        .title(this.title)
                        .introduction(this.introduction)
                        .userId(this.userId)
                        .graduateDate(this.graduateDate)
                        .build();
            }
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PACKAGE)
        public static class Sticker {
            private Long num;
            private Double positionX;
            private Double positionY;
            private Long img;
            private Double width;
            private Double angle;
            private Long mirror;

            public BlackBoardStickers toEntity() {
                return BlackBoardStickers.builder()
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {

        private String userId;

        @Builder
        public Res(String userId) {
            this.userId = userId;
        }
    }
}
