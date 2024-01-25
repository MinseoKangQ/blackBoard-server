package dev.line4.blackBoard.lettersticker.repository;

import dev.line4.blackBoard.letter.entity.Letters;
import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LetterStickerRepository extends JpaRepository<LetterStickers, Long> {
    List<LetterStickers> findAllByLetter(Letters letter);
}
