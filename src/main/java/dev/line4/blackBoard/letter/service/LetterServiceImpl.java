package dev.line4.blackBoard.letter.service;

import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import dev.line4.blackBoard.blackboard.repository.BlackBoardRepository;
import dev.line4.blackBoard.letter.dto.LetterReqDto;
import dev.line4.blackBoard.letter.dto.LetterResDto;
import dev.line4.blackBoard.letter.dto.VisitorResDto;
import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.letter.repository.LetterRepository;
import dev.line4.blackBoard.lettersticker.service.LetterStickerServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService {

    private final BlackBoardRepository boardRepository;
    private final LetterRepository letterRepository;
    private final LetterStickerServiceImpl letterStickerService;

    @Override
    public LetterResDto createLetter(LetterReqDto dto, String blackboardId) {

        Optional<BlackBoards> existingBlackBoard = boardRepository.findById(blackboardId);

        if (existingBlackBoard.isEmpty()) {
            System.out.println("칠판이 존재하지 않음");
            return null;
        }

        // Letter 빌드
        Letters newLetter = Letters.builder()
                .nickname(dto.getNickname())
                .content(dto.getContent())
                .font(dto.getFont())
                .align(dto.getAlign())
                .blackboard(boardRepository.findById(blackboardId).get())
                .build();

        // Letter 저장
        Letters savedLetter = letterRepository.save(newLetter);

        // LetterSticker 들 저장
        letterStickerService.createLetterStickers(dto.getStickers(), savedLetter);

        // 리턴값 (url) 빌드
        LetterResDto resDto = LetterResDto.builder()
                .url(blackboardId)
                .build();

        return resDto;

    }

    @Override
    public VisitorResDto readVisitor(String blackboardId) {

        // 칠판 찾기
        Optional<BlackBoards> existingBlackBoard = boardRepository.findById(blackboardId);

        if (existingBlackBoard.isEmpty()) {
            System.out.println("칠판이 존재하지 않음");
            return null;
        }

        // 찾은 칠판 엔티티로 letter list 조회
        List<Letters> letters = existingBlackBoard.get().getLetters();

        // letter 의 nickname 만 추출해서 리스터트에 담기
        List<String> nicknames = letters.stream()
                .map(Letters::getNickname)
                .collect(Collectors.toList());

        // 리턴값 빌드
        VisitorResDto resDto = VisitorResDto.builder()
                .nickname(nicknames)
                .build();

        return resDto;

    }

}
