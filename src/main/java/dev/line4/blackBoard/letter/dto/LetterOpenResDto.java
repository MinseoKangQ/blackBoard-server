package dev.line4.blackBoard.letter.dto;

import dev.line4.blackBoard.lettersticker.dto.LetterStickerReqDto;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LetterOpenResDto {
    @ApiModelProperty(notes = "편지 생성 순서", example = "1")
    private Long id;
    @ApiModelProperty(notes = "편지 작성자", example = "8팀 백엔드")
    private String nickname;
    @ApiModelProperty(notes = "편지 내용", example = "<div class=\"true\" class=\"sc-hHEhDq gogfU\" style=\"text-align: left;\"><span class=\"blue\">대학원생 축하드려요....</span></div>")
    private String content;
    @ApiModelProperty(notes = "폰트", example = "Dongguk")
    private String font;
    @ApiModelProperty(notes = "정렬", example = "center")
    private String align;
    @ApiModelProperty(notes = "편지 스티커", example = "[\n"
            + "    {\n"
            + "      \"num\" : 1,\n"
            + "      \"positionX\" : 0.5,\n"
            + "\t    \"positionY\" : 0.3,\n"
            + "\t    \"img\" : 3,\n"
            + "\t    \"width\" : 1.5,\n"
            + "\t\t\t\"angle\" : 3.4,\n"
            + "\t\t\t\"mirror\" : -1\n"
            + "    },\n"
            + "    {\n"
            + "      \"num\" : 2,\n"
            + "      \"positionX\" : 0.8,\n"
            + "\t    \"positionY\" : 0.4,\n"
            + "\t    \"img\" : 3,\n"
            + "\t    \"width\" : 1.6,\n"
            + "\t\t\t\"angle\" : 3.4,\n"
            + "\t\t\t\"mirror\" : -1\n"
            + "    },\n"
            + "\t\t{\n"
            + "      \"num\" : 3,\n"
            + "      \"positionX\" : 0.7,\n"
            + "\t    \"positionY\" : 0.1,\n"
            + "\t    \"img\" : 3,\n"
            + "\t    \"width\" : 1.7,\n"
            + "\t\t\t\"angle\" : 3.4,\n"
            + "\t\t\t\"mirror\" : 1\n"
            + "    }\n"
            + "  ]")
    private List<LetterStickerReqDto> stickers;
}
