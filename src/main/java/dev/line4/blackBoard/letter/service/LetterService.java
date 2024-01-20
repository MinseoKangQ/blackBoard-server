package dev.line4.blackBoard.letter.service;

import dev.line4.blackBoard.letter.dto.LetterReqDto;
import dev.line4.blackBoard.letter.dto.LetterResDto;
import dev.line4.blackBoard.letter.dto.VisitorResDto;

public interface LetterService {
    LetterResDto createLetter(LetterReqDto dto, String blackboardId);

    VisitorResDto readVisitor(String blackboardId);
}
