package dev.line4.blackBoard.blackboard.service;

import dev.line4.blackBoard.blackboard.dto.GetBlackBoardCountDto;
import dev.line4.blackBoard.blackboard.dto.BlackBoardOpenResDto;
import dev.line4.blackBoard.blackboard.dto.BlackBoardReqDto;
import dev.line4.blackBoard.blackboard.dto.BlackBoardResDto;
import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import dev.line4.blackBoard.blackboard.repository.BlackBoardRepository;
import dev.line4.blackBoard.blackboardsticker.dto.BlackBoardStickerResDto;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import dev.line4.blackBoard.blackboardsticker.service.BlackBoardStickerService;
import dev.line4.blackBoard.letter.dto.LetterOpenResDto;
import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.lettersticker.dto.LetterStickerReqDto;
import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.line4.blackBoard.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackBoardServiceImpl implements BlackBoardService{

    private final BlackBoardRepository blackBoardRepository;
    private BlackBoardStickerService blackBoardStickerService;

    // 완료
    // 생성된 칠판의 개수 가져오기
    @Override
    public ResponseEntity<ApiResponse<?>> getBlackBoardCount() {
        GetBlackBoardCountDto.Res data = new GetBlackBoardCountDto.Res(blackBoardRepository.count());
        ApiResponse<GetBlackBoardCountDto.Res> res = ApiResponse.success(data, "칠판 개수 조회 성공");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public BlackBoardResDto createBlackBoard(BlackBoardReqDto blackBoardReqDto) {
        String randomUrl = UUID.randomUUID().toString().substring(0, 12);

        BlackBoards blackBoards = BlackBoards.builder()
                .id(randomUrl)
                .title(blackBoardReqDto.getTitle())
                .introduction(blackBoardReqDto.getIntroduction())
                .email(blackBoardReqDto.getEmail())
                .graduateDate(blackBoardReqDto.getGraduateDate())
                .build();
        BlackBoards savedBlackBoard = blackBoardRepository.save(blackBoards);

        blackBoardStickerService.createBlackBoardStickers(blackBoardReqDto.getStickers(), savedBlackBoard);

        return BlackBoardResDto.builder().url(randomUrl).build();
    }

    public BlackBoardOpenResDto getBlackBoardAndLetter(String blackboardId) {
        BlackBoards blackBoard = blackBoardRepository.findById(blackboardId)
                .orElseThrow(() -> new RuntimeException("Blackboard not found with id: " + blackboardId));
        List<BlackBoardStickerResDto> boardStickers = mapToBlackBoardStickerResDtos(blackBoard.getBlackBoardStickers());
        List<LetterOpenResDto> letters = mapToLetterOpenResDtos(blackBoard.getLetters());

        return BlackBoardOpenResDto.builder()
                .title(blackBoard.getTitle())
                .introduction(blackBoard.getIntroduction())
                .graduateDate(blackBoard.getGraduateDate())
                .stickers(boardStickers)
                .letters(letters)
                .build();
    }

    // 원래 private
    public List<BlackBoardStickerResDto> mapToBlackBoardStickerResDtos(Set<BlackBoardStickers> blackBoardStickers) {
        return blackBoardStickers.stream()
                .map(this::mapToBlackBoardStickerResDto)
                .collect(Collectors.toList());
    }

    // 원래 private
    // blackboard에 sticker 연결
    public BlackBoardStickerResDto mapToBlackBoardStickerResDto(BlackBoardStickers sticker) {
        return BlackBoardStickerResDto.builder()
                .num(sticker.getNum())
                .positionX(sticker.getPositionX())
                .positionY(sticker.getPositionY())
                .img(sticker.getImg())
                .width(sticker.getWidth())
                .angle(sticker.getAngle())
                .mirror(sticker.getMirror())
                .build();
    }

    // 원래 private
    // blackboard에 letter 연결
    public List<LetterOpenResDto> mapToLetterOpenResDtos(List<Letters> letters) {
        return letters.stream()
                .map(this::mapToLetterOpenResDto)
                .collect(Collectors.toList());
    }

    // 원래 private
    public LetterOpenResDto mapToLetterOpenResDto(Letters letter) {
        List<LetterStickerReqDto> stickers = mapToLetterStickerReqDtos(letter.getStickers());

        return LetterOpenResDto.builder()
                .id(letter.getLetterId())
                .nickname(letter.getNickname())
                .content(letter.getContent())
                .font(letter.getFont())
                .align(letter.getAlign())
                .stickers(stickers)
                .build();
    }

    // 원래 private
    // letter에 letterStickers 연결
    public List<LetterStickerReqDto> mapToLetterStickerReqDtos(List<LetterStickers> letterStickers) {
        return letterStickers.stream()
                .map(this::mapToLetterStickerReqDto)
                .collect(Collectors.toList());
    }

    // 원래 private
    public LetterStickerReqDto mapToLetterStickerReqDto(LetterStickers letterSticker) {
        return LetterStickerReqDto.builder()
                .num(letterSticker.getNum())
                .positionX(letterSticker.getPositionX())
                .positionY(letterSticker.getPositionY())
                .img(letterSticker.getImg())
                .width(letterSticker.getWidth())
                .angle(letterSticker.getAngle())
                .mirror(letterSticker.getMirror())
                .build();
    }
}
