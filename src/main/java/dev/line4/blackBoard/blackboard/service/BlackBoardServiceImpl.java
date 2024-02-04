package dev.line4.blackBoard.blackboard.service;

import dev.line4.blackBoard.blackboard.dto.AddBlackBoardDto;
import dev.line4.blackBoard.blackboard.dto.ReadBlackBoardAndLettersDto;
import dev.line4.blackBoard.blackboard.dto.CountBlackBoardsDto;
import dev.line4.blackBoard.blackboard.entity.BlackBoardEntity;
import dev.line4.blackBoard.blackboard.repository.BlackBoardRepository;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickerEntity;
import dev.line4.blackBoard.blackboardsticker.service.BlackBoardStickerServiceImpl;
import dev.line4.blackBoard.letter.entity.LetterEntity;
import dev.line4.blackBoard.letter.service.LetterServiceImpl;
import dev.line4.blackBoard.utils.exception.service.ServiceUtils;
import dev.line4.blackBoard.utils.response.ApiResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackBoardServiceImpl implements BlackBoardService {

    // repository
    private final BlackBoardRepository blackBoardRepository;

    // service
    private final BlackBoardStickerServiceImpl blackBoardStickerService;
    private final LetterServiceImpl letterService;

    // 완료
    // 생성된 칠판의 개수 가져오기
    @Override
    public ResponseEntity<ApiResponse<?>> countBlackBoards() {
        CountBlackBoardsDto.Res data = new CountBlackBoardsDto.Res(blackBoardRepository.count());
        ApiResponse<CountBlackBoardsDto.Res> res = ApiResponse.createSuccessWithData(data, "칠판 개수 조회 성공");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // 완료
    // 칠판 생성하기
    @Override
    public ResponseEntity<ApiResponse<?>> addBlackBoard(AddBlackBoardDto.Req req) {

        // 칠판 엔티티 생성
        BlackBoardEntity blackBoard = BlackBoardEntity.createBlackBoard(req);

        // 칠판 엔티티 저장 (** 칠판 저장 후 칠판 스티커 저장 **)
        blackBoardRepository.save(blackBoard);

        // 칠판 스티커 엔티티 저장 + 칠판 엔티티와 연관관계
        List<BlackBoardStickerEntity> stickers = blackBoardStickerService.createStickers(req.getStickers(), blackBoard);

        // 응답할 데이터 생성
        AddBlackBoardDto.Res data = new AddBlackBoardDto.Res(req.getBlackboard().getUserId());
        ApiResponse<AddBlackBoardDto.Res> res = ApiResponse.createSuccessWithData(data, "칠판이 정상적으로 등록되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }

    // 완료
    // 칠판, 편지 조회
    @Override
    public ResponseEntity<ApiResponse<?>> readBlackBoardAndLetters(String userId) {

        // 칠판 찾고 가져오기
        BlackBoardEntity foundBlackBoard = ServiceUtils.getEntityOrThrow(
                blackBoardRepository.findBlackBoardsByUserId(userId),
                "칠판을 찾을 수 없습니다."
        );

        // 칠판 스티커 엔티티 가져오기
        List<BlackBoardStickerEntity> blackBoardStickers = foundBlackBoard.getBlackBoardStickers();

        // 편지 엔티티 가져오기
        List<LetterEntity> letters = foundBlackBoard.getLetters();

        // 칠판 스티커 응답 (dto)
        List<ReadBlackBoardAndLettersDto.Sticker> resBlackBoardSticker = blackBoardStickerService.convertToBlackBoardStickerDtoList(foundBlackBoard);

        // 칠판 응답 (dto)
        ReadBlackBoardAndLettersDto.Res.BlackBoard resBlackBoard = convertToBlackBoardDto(foundBlackBoard, resBlackBoardSticker);

        // 편지 + 편지 스티커 응답 (dto)
        List<ReadBlackBoardAndLettersDto.Res.Letter> resLetter = letterService.convertToLetterAndLetterStickerDtoList(letters);

        // 응답 데이터 생성
        ReadBlackBoardAndLettersDto.Res data = ReadBlackBoardAndLettersDto.Res.builder()
                .blackboard(resBlackBoard) // 칠판 + 칠판 스티커
                .letter(resLetter) // 편지 + 편지 스티커
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessWithData(data, "칠판과 편지가 성공적으로 조회되었습니다."));
    }

    // 사용자의 이름 중복인지 확인
    @Override
    public ResponseEntity<ApiResponse<?>> checkDuplicateUserId(String userId) {
        // userId를 기준으로 중복 검사
        boolean isDuplicate = blackBoardRepository.existsByUserId(userId);

        if (isDuplicate) {
            // 중복된 userId가 존재할 경우
            ApiResponse<String> errorResponse = ApiResponse.createFailWithoutData(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 URL 입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {
            // 중복된 userId가 없을 경우
            ApiResponse<String> successResponse = ApiResponse.createSuccessWithoutData(HttpStatus.OK.value(), "사용 가능한 URL 입니다.");
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        }
    }

    private static ReadBlackBoardAndLettersDto.Res.BlackBoard convertToBlackBoardDto(BlackBoardEntity foundBlackBoard, List<ReadBlackBoardAndLettersDto.Sticker> resBlackBoardSticker) {
        return ReadBlackBoardAndLettersDto.Res.BlackBoard.builder()
                .title(foundBlackBoard.getTitle())
                .introduction(foundBlackBoard.getIntroduction())
                .userId(foundBlackBoard.getUserId())
                .openDate(foundBlackBoard.getOpenDate())
                .stickers(resBlackBoardSticker)
                .build();
    }

}
