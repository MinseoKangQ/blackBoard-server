package dev.line4.blackBoard.lettersticker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LetterStickerReqDto {
    @ApiModelProperty(notes = "스티커 생성 순서", example = "1")
    private Long num;
    @ApiModelProperty(notes = "x 좌표", example = "0.5")
    private Double positionX;
    @ApiModelProperty(notes = "y 좌표", example = "0.3")
    private Double positionY;
    @ApiModelProperty(notes = "종류", example = "3")
    private Long img;
    @ApiModelProperty(notes = "크기", example = "1.5")
    private Double width;
    @ApiModelProperty(notes = "각도", example = "0.5")
    private Double angle;
    @ApiModelProperty(notes = "반전 여부", example = "1")
    private Long mirror;
}
