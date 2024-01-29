package dev.line4.blackBoard.lettersticker.repository;

import dev.line4.blackBoard.letter.entity.LetterEntity;
import dev.line4.blackBoard.lettersticker.entity.LetterStickerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterStickerRepository extends JpaRepository<LetterStickerEntity, Long> {
    List<LetterStickerEntity> findAllByLetter(LetterEntity letter);
}
