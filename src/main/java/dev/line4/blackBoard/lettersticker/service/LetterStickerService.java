package dev.line4.blackBoard.lettersticker.service;

import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.lettersticker.dto.LetterStickerReqDto;
import java.util.List;

public interface LetterStickerService {
    void createLetterStickers(List<LetterStickerReqDto> stickerDtoList, Letters letter);
}
