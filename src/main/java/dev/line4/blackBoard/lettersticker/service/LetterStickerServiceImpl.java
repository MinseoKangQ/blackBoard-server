package dev.line4.blackBoard.lettersticker.service;

import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.lettersticker.dto.LetterStickerReqDto;
import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import dev.line4.blackBoard.lettersticker.repository.LetterStickerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterStickerServiceImpl implements LetterStickerService {

    private final LetterStickerRepository letterStickerRepository;

    @Override
    public void createLetterStickers(List<LetterStickerReqDto> stickerDtoList, Letters letter) {
        for (LetterStickerReqDto stickerDto : stickerDtoList) {
            LetterStickers sticker = LetterStickers.builder()
                    .num(stickerDto.getNum())
                    .positionX(stickerDto.getPositionX())
                    .positionY(stickerDto.getPositionY())
                    .img(stickerDto.getImg())
                    .width(stickerDto.getWidth())
                    .angle(stickerDto.getAngle())
                    .mirror(stickerDto.getMirror())
                    .letter(letter)
                    .build();
            letterStickerRepository.save(sticker);
        }
    }

}
