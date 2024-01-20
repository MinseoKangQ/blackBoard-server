package dev.line4.blackBoard.blackboard.dto;

import dev.line4.blackBoard.blackboardsticker.dto.BlackBoardStickerReqDto;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlackBoardReqDto {
    @ApiModelProperty(notes = "칠판 제목", example = "4호선톤 모두 고생했어!")
    private String title;

    @ApiModelProperty(notes = "칠판 소개", example = "아기사자들 모두 마지막까지 화이팅!")
    private String introduction;

    @ApiModelProperty(notes = "이메일", example = "lin4@email.com")
    private String email;

    @ApiModelProperty(notes = "졸업 일시", example = "2023-11-11 17:00:00")
    private String graduateDate;

    @ApiModelProperty(notes = "칠판 스티커", example = "[\n"
            + "        {\n"
            + "            \"num\": 1,\n"
            + "            \"positionX\": 0.7,\n"
            + "            \"positionY\": 0.1,\n"
            + "            \"img\": 3,\n"
            + "            \"width\": 1.7,\n"
            + "            \"angle\": 3.4,\n"
            + "            \"mirror\": -1\n"
            + "        },\n"
            + "        {\n"
            + "            \"num\": 1,\n"
            + "            \"positionX\": 0.5,\n"
            + "            \"positionY\": 0.3,\n"
            + "            \"img\": 3,\n"
            + "            \"width\": 1.5,\n"
            + "            \"angle\": 3.4,\n"
            + "            \"mirror\": -1\n"
            + "        },\n"
            + "        {\n"
            + "            \"num\": 3,\n"
            + "            \"positionX\": 0.8,\n"
            + "            \"positionY\": 0.4,\n"
            + "            \"img\": 3,\n"
            + "            \"width\": 1.6,\n"
            + "            \"angle\": 3.4,\n"
            + "            \"mirror\": -1\n"
            + "        }\n"
            + "    ]")
    private List<BlackBoardStickerReqDto> stickers;
}
