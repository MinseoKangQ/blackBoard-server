package dev.line4.blackBoard.blackboard.dto;

import dev.line4.blackBoard.blackboardsticker.dto.BlackBoardStickerResDto;
import dev.line4.blackBoard.letter.dto.LetterOpenResDto;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlackBoardOpenResDto {
    @ApiModelProperty(notes = "칠판 제목", example = "4호선톤 모두 고생했어!")
    private String title;

    @ApiModelProperty(notes = "칠판 소개", example = "아기사자들 모두 마지막까지 화이팅!")
    private String introduction;

    @ApiModelProperty(notes = "졸업 일시", example = "2023-11-11 17:00:00")
    private String graduateDate;

    @ApiModelProperty(notes = "칠판 스티커", example = "[\n"
            + "        {\n"
            + "            \"num\": 1,\n"
            + "            \"positionX\": 14.280133928571427,\n"
            + "            \"positionY\": -2.245744977678571,\n"
            + "            \"img\": 3,\n"
            + "            \"width\": 1.7,\n"
            + "            \"angle\": 3.4,\n"
            + "            \"mirror\": 1\n"
            + "        },\n"
            + "        {\n"
            + "            \"num\": 2,\n"
            + "            \"positionX\": 14.280133928571427,\n"
            + "            \"positionY\": -2.245744977678571,\n"
            + "            \"img\": 2,\n"
            + "            \"width\": 1.5,\n"
            + "            \"angle\": 0,\n"
            + "            \"mirror\": -1\n"
            + "        },\n"
            + "        {\n"
            + "            \"num\": 2,\n"
            + "            \"positionX\": 0.8,\n"
            + "            \"positionY\": 0.4,\n"
            + "            \"img\": 3,\n"
            + "            \"width\": 1.6,\n"
            + "            \"angle\": 3.4,\n"
            + "            \"mirror\": -1\n"
            + "        }\n"
            + "    ]")
    private List<BlackBoardStickerResDto> stickers;

    @ApiModelProperty(notes = "칠판 편지 정보", example = "[\n"
            + "        {\n"
            + "            \"id\": 1,\n"
            + "            \"nickname\": \"배포닉네임\",\n"
            + "            \"content\": \"제발되게해주세요\",\n"
            + "            \"font\": \"Alien\",\n"
            + "            \"align\": \"center\",\n"
            + "            \"stickers\": [\n"
            + "                {\n"
            + "                    \"num\": 1,\n"
            + "                    \"positionX\": 0.5,\n"
            + "                    \"positionY\": 0.3,\n"
            + "                    \"img\": 3,\n"
            + "                    \"width\": 1.5,\n"
            + "                    \"angle\": 0.5,\n"
            + "                    \"mirror\": -1\n"
            + "                },\n"
            + "                {\n"
            + "                    \"num\": 2,\n"
            + "                    \"positionX\": 0.7,\n"
            + "                    \"positionY\": 0.4,\n"
            + "                    \"img\": 7,\n"
            + "                    \"width\": 1.2,\n"
            + "                    \"angle\": 0.7,\n"
            + "                    \"mirror\": 1\n"
            + "                }\n"
            + "            ]\n"
            + "        }\n"
            + "    ]")
    private List<LetterOpenResDto> letters;
}
