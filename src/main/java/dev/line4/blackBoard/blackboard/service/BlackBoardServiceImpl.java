package dev.line4.blackBoard.blackboard.service;

import dev.line4.blackBoard.blackboard.dto.BlackBoardOpenResDto;
import dev.line4.blackBoard.blackboard.dto.CreateBlackBoardDto;
import dev.line4.blackBoard.blackboard.dto.GetBlackBoardCountDto;
import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import dev.line4.blackBoard.blackboard.repository.BlackBoardRepository;
import dev.line4.blackBoard.blackboardsticker.dto.BlackBoardStickerResDto;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import dev.line4.blackBoard.blackboardsticker.repository.BlackBoardStickerRepository;
import dev.line4.blackBoard.blackboardsticker.service.BlackBoardStickerService;
import dev.line4.blackBoard.letter.dto.LetterOpenResDto;
import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.lettersticker.dto.LetterStickerReqDto;
import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import dev.line4.blackBoard.utils.response.ApiResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackBoardServiceImpl implements BlackBoardService {

    private final BlackBoardRepository blackBoardRepository;
    private final BlackBoardStickerRepository blackBoardStickerRepository;
    private BlackBoardStickerService blackBoardStickerService;

    // 완료
    // 생성된 칠판의 개수 가져오기
    @Override
    public ResponseEntity<ApiResponse<?>> getBlackBoardCount() {
        GetBlackBoardCountDto.Res data = new GetBlackBoardCountDto.Res(blackBoardRepository.count());
        ApiResponse<GetBlackBoardCountDto.Res> res = ApiResponse.success(data, "칠판 개수 조회 성공");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // 완료
    // 칠판 생성하기
    @Override
    public ResponseEntity<ApiResponse<?>> createBlackBoard(CreateBlackBoardDto.Req req) {

        // 칠판 엔티티 생성
        BlackBoards blackBoard = BlackBoards.createBlackBoard(req);

        // 스티커 엔티티 생성, 칠판 엔티티와 연관관계 맺기
        for (CreateBlackBoardDto.Req.Sticker stickerDto : req.getStickers()) {
            BlackBoardStickers stickers = BlackBoardStickers.createBlackBoardSticker(stickerDto);
            stickers.setBoard(blackBoard);
            blackBoard.getBlackBoardStickers().add(stickers);
        }

        // 저장
        blackBoardRepository.save(blackBoard);
        blackBoardStickerRepository.saveAll(blackBoard.getBlackBoardStickers());

        // 응답할 데이터 생성
        CreateBlackBoardDto.Res data = new CreateBlackBoardDto.Res(req.getBlackboard().getUserId());
        ApiResponse<CreateBlackBoardDto.Res> res = ApiResponse.success(data, "칠판 생성 성공");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // 사용자의 이름 중복인지 확인
    @Override
    public ResponseEntity<ApiResponse<?>> checkDuplicateUserId(String userId) {
        // userId를 기준으로 중복 검사
        boolean isDuplicate = blackBoardRepository.existsByUserId(userId);

        if (isDuplicate) {
            // 중복된 userId가 존재할 경우
            ApiResponse<String> errorResponse = ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 URL 입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {
            // 중복된 userId가 없을 경우
            ApiResponse<String> successResponse = ApiResponse.success(HttpStatus.OK.value(), "사용 가능한 URL 입니다.");
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        }
    }


    @Override
    public BlackBoardOpenResDto getBlackBoardAndLetter(String blackboardId) {
        return null;
    }

//    public BlackBoardOpenResDto getBlackBoardAndLetter(String blackboardId) {
//        BlackBoards blackBoard = blackBoardRepository.findById(blackboardId)
//                .orElseThrow(() -> new RuntimeException("Blackboard not found with id: " + blackboardId));
//        List<BlackBoardStickerResDto> boardStickers = mapToBlackBoardStickerResDtos(blackBoard.getBlackBoardStickers());
//        List<LetterOpenResDto> letters = mapToLetterOpenResDtos(blackBoard.getLetters());
//
//        return BlackBoardOpenResDto.builder()
//                .title(blackBoard.getTitle())
//                .introduction(blackBoard.getIntroduction())
//                .openDate(blackBoard.getOpenDate())
//                .stickers(boardStickers)
//                .letters(letters)
//                .build();
//    }

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
