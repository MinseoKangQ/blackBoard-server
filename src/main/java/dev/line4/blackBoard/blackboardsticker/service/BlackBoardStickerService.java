package dev.line4.blackBoard.blackboardsticker.service;

import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import dev.line4.blackBoard.blackboardsticker.dto.BlackBoardStickerReqDto;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import dev.line4.blackBoard.blackboardsticker.repository.BlackBoardStickerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BlackBoardStickerService {
    private final BlackBoardStickerRepository blackBoardStickerRepository;

    public BlackBoardStickerService(BlackBoardStickerRepository blackBoardStickerRepository) {
        this.blackBoardStickerRepository = blackBoardStickerRepository;
    }

    public void createBlackBoardStickers(List<BlackBoardStickerReqDto> stickerDtos, BlackBoards blackboard) {

        for (BlackBoardStickerReqDto stickerDto : stickerDtos) {
            BlackBoardStickers blackBoardStickers = BlackBoardStickers.builder()
                    .num(stickerDto.getNum())
                    .positionX(stickerDto.getPositionX())
                    .positionY(stickerDto.getPositionY())
                    .img(stickerDto.getImg())
                    .width(stickerDto.getWidth())
                    .angle(stickerDto.getAngle())
                    .mirror(stickerDto.getMirror())
                    .boardId(blackboard)
                    .build();

            blackBoardStickerRepository.save(blackBoardStickers);
        }

    }
}
