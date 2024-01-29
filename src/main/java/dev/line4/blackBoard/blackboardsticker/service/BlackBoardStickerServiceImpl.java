package dev.line4.blackBoard.blackboardsticker.service;

import dev.line4.blackBoard.blackboard.dto.AddBlackBoardDto;
import dev.line4.blackBoard.blackboard.dto.ReadBlackBoardAndLettersDto;
import dev.line4.blackBoard.blackboard.entity.BlackBoardEntity;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickerEntity;
import dev.line4.blackBoard.blackboardsticker.repository.BlackBoardStickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlackBoardStickerServiceImpl implements BlackBoardStickerService {

    private final BlackBoardStickerRepository blackBoardStickerRepository;

    public List<BlackBoardStickerEntity> createStickers(List<AddBlackBoardDto.Req.Sticker> stickerList, BlackBoardEntity blackBoard) {

        // 스티커 엔티티 생성 및 초기화
        List<BlackBoardStickerEntity> stickers = stickerList.stream()
                .map(stickerDto -> {
                    BlackBoardStickerEntity sticker = BlackBoardStickerEntity.createBlackBoardSticker(stickerDto);
                    sticker.setBlackBoard(blackBoard);
                    return sticker;
                })
                .collect(Collectors.toList());

        // 스티커 엔티티 저장
        blackBoardStickerRepository.saveAll(stickers);

        return stickers;
    }

    // 칠판 스티커 DTO 변환 메서드
    public List<ReadBlackBoardAndLettersDto.Sticker> convertToBlackBoardStickerDtoList(BlackBoardEntity blackBoard) {
        return blackBoard.getBlackBoardStickers().stream()
                .map(sticker -> ReadBlackBoardAndLettersDto.Sticker.builder()
                        .num(sticker.getNum())
                        .positionX(sticker.getPositionX())
                        .positionY(sticker.getPositionY())
                        .img(sticker.getImg())
                        .width(sticker.getWidth())
                        .angle(sticker.getAngle())
                        .mirror(sticker.getMirror())
                        .build())
                .collect(Collectors.toList());
    }
}
