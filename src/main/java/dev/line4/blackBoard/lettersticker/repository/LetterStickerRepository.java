package dev.line4.blackBoard.lettersticker.repository;

import dev.line4.blackBoard.lettersticker.entity.LetterStickers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterStickerRepository extends JpaRepository<LetterStickers, Long> {
}
