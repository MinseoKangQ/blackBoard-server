package dev.line4.blackBoard.blackboardsticker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlackBoardStickerReqDto {
    private Long num;
    private Double positionX;
    private Double positionY;
    private Long img;
    private Double width;
    private Double angle;
    private Long mirror;
    private Long boardId;
}
