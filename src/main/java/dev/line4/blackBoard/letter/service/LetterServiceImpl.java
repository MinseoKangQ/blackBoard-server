package dev.line4.blackBoard.letter.service;

import dev.line4.blackBoard.blackboard.dto.ReadBlackBoardAndLettersDto;
import dev.line4.blackBoard.blackboard.entity.BlackBoardEntity;
import dev.line4.blackBoard.blackboard.repository.BlackBoardRepository;
import dev.line4.blackBoard.letter.dto.AddLetterDto;
import dev.line4.blackBoard.letter.dto.GetLetterWritersDto;
import dev.line4.blackBoard.letter.entity.LetterEntity;
import dev.line4.blackBoard.letter.repository.LetterRepository;
import dev.line4.blackBoard.lettersticker.entity.LetterStickerEntity;
import dev.line4.blackBoard.lettersticker.repository.LetterStickerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import dev.line4.blackBoard.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService {

    private final BlackBoardRepository blackBoardRepository;
    private final LetterRepository letterRepository;
    private final LetterStickerRepository letterStickerRepository;

    // 완료
    // 편지 생성하기
    @Override
    public ResponseEntity<ApiResponse<?>> addLetter(String userId, AddLetterDto.Req req) {

        // userId 로 칠판 찾기, 없으면 에러 응답
        Optional<BlackBoardEntity> findBlackBoard = blackBoardRepository.findBlackBoardsByUserId(userId);
        if(findBlackBoard.isEmpty()) {
            ApiResponse<Object> res = ApiResponse.createFailWithoutData(404, "칠판을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        // 찾은 칠판 엔티티
        BlackBoardEntity foundBlackBoard = findBlackBoard.get();

        // 편지 엔티티 생성
        LetterEntity letter = LetterEntity.createLetter(req.getLetter());

        // 편지 엔티티와 칠판 엔티티 연관관계 맺기
        letter.setBlackBoard(foundBlackBoard);

        // 편지 스티커 엔티티 생성, 편지 엔티티와 연관관계 맺기
        for(AddLetterDto.Req.Sticker sticker : req.getStickers()) {
            LetterStickerEntity stickers  = LetterStickerEntity.createLetterSticker(sticker);
            stickers.setLetter(letter);
            letter.getLetterStickers().add(stickers);
        }

        // 저장
        letterRepository.save(letter);
        letterStickerRepository.saveAll(letter.getLetterStickers());

        // 응답할 데이터 생성
        ApiResponse<Object> res = ApiResponse.createSuccessWithData(null, "편지가 정상적으로 등록되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getLetterWriters(String userId) {

        // userId 로 칠판 찾기, 없으면 에러 응답
        Optional<BlackBoardEntity> findBlackBoard = blackBoardRepository.findBlackBoardsByUserId(userId);
        if(findBlackBoard.isEmpty()) {
            ApiResponse<Object> res = ApiResponse.createFailWithoutData(404, "칠판을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        // 편지들 찾기
        BlackBoardEntity foundBlackBoard = findBlackBoard.get();
        List<LetterEntity> letters = foundBlackBoard.getLetters();

        // 작성자만 추출
        List<String> writers = letters.stream()
                .map(LetterEntity::getNickname)
                .collect(Collectors.toList());

        // 응답할 데이터 생성
        GetLetterWritersDto.Res resData = GetLetterWritersDto.Res.builder()
                .writers(writers)
                .build();

        ApiResponse<GetLetterWritersDto.Res> res = ApiResponse.createSuccessWithData(resData, "편지 작성자가 정상적으로 조회되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Override
    public List<ReadBlackBoardAndLettersDto.Res.Letter> convertToLetterAndLetterStickerDtoList(List<LetterEntity> letters) {
        return letters.stream().map(letter -> {
            // 편지 스티커 엔티티 가져오기 및 DTO 변환
            List<ReadBlackBoardAndLettersDto.Sticker> resLetterSticker = letter.getLetterStickers().stream()
                    .map(letterSticker -> ReadBlackBoardAndLettersDto.Sticker.builder()
                            .num(letterSticker.getNum())
                            .positionX(letterSticker.getPositionX())
                            .positionY(letterSticker.getPositionY())
                            .img(letterSticker.getImg())
                            .width(letterSticker.getWidth())
                            .angle(letterSticker.getAngle())
                            .mirror(letterSticker.getMirror())
                            .build())
                    .collect(Collectors.toList());

            // 편지 DTO 구성
            return ReadBlackBoardAndLettersDto.Res.Letter.builder()
                    .id(letter.getId())
                    .nickname(letter.getNickname())
                    .content(letter.getContent())
                    .font(letter.getFont())
                    .align(letter.getAlign())
                    .stickers(resLetterSticker)
                    .build();
        }).collect(Collectors.toList());
    }

}
