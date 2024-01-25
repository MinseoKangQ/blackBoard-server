package dev.line4.blackBoard.letter.service;

import dev.line4.blackBoard.blackboard.dto.GetBlackBoardAndLetterDto;
import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import dev.line4.blackBoard.blackboard.repository.BlackBoardRepository;
import dev.line4.blackBoard.blackboardsticker.repository.BlackBoardStickerRepository;
import dev.line4.blackBoard.letter.dto.CreateLetterDto;
import dev.line4.blackBoard.letter.dto.ReadWriterDto;
import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.letter.repository.LetterRepository;
import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import dev.line4.blackBoard.lettersticker.repository.LetterStickerRepository;
import dev.line4.blackBoard.lettersticker.service.LetterStickerServiceImpl;
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
    public ResponseEntity<ApiResponse<?>> createLetter(String userId, CreateLetterDto.Req req) {

        // userId 로 칠판 찾기, 없으면 에러 응답
        Optional<BlackBoards> findBlackBoard = blackBoardRepository.findBlackBoardsByUserId(userId);
        if(findBlackBoard.isEmpty()) {
            ApiResponse<Object> res = ApiResponse.fail(404, "칠판을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        // 찾은 칠판 엔티티
        BlackBoards foundBlackBoard = findBlackBoard.get();

        // 편지 엔티티 생성
        Letters letter = Letters.createLetter(req.getLetter());

        // 편지 엔티티와 칠판 엔티티 연관관계 맺기
        letter.setBlackBoard(foundBlackBoard);

        // 편지 스티커 엔티티 생성, 편지 엔티티와 연관관계 맺기
        for(CreateLetterDto.Req.Sticker sticker : req.getStickers()) {
            LetterStickers stickers  = LetterStickers.createLetterSticker(sticker);
            stickers.setLetter(letter);
            letter.getLetterStickers().add(stickers);
        }

        // 저장
        letterRepository.save(letter);
        letterStickerRepository.saveAll(letter.getLetterStickers());

        // 응답할 데이터 생성
        ApiResponse<Object> res = ApiResponse.success(null, "편지가 정상적으로 등록되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> readWriter(String userId) {

        // userId 로 칠판 찾기, 없으면 에러 응답
        Optional<BlackBoards> findBlackBoard = blackBoardRepository.findBlackBoardsByUserId(userId);
        if(findBlackBoard.isEmpty()) {
            ApiResponse<Object> res = ApiResponse.fail(404, "칠판을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        // 편지들 찾기
        BlackBoards foundBlackBoard = findBlackBoard.get();
        List<Letters> letters = foundBlackBoard.getLetters();

        // 작성자만 추출
        List<String> writers = letters.stream()
                .map(Letters::getNickname)
                .collect(Collectors.toList());

        // 응답할 데이터 생성
        ReadWriterDto.Res resData = ReadWriterDto.Res.builder()
                .writers(writers)
                .build();

        ApiResponse<ReadWriterDto.Res> res = ApiResponse.success(resData, "편지 작성자가 정상적으로 조회되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Override
    public List<GetBlackBoardAndLetterDto.Res.Letter> convertToLetterAndLetterStickerDtoList(List<Letters> letters) {
        return letters.stream().map(letter -> {
            // 편지 스티커 엔티티 가져오기 및 DTO 변환
            List<GetBlackBoardAndLetterDto.Sticker> resLetterSticker = letter.getLetterStickers().stream()
                    .map(letterSticker -> GetBlackBoardAndLetterDto.Sticker.builder()
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
            return GetBlackBoardAndLetterDto.Res.Letter.builder()
                    .id(letter.getLetterId())
                    .nickname(letter.getNickname())
                    .content(letter.getContent())
                    .font(letter.getFont())
                    .align(letter.getAlign())
                    .stickers(resLetterSticker)
                    .build();
        }).collect(Collectors.toList());
    }

}
