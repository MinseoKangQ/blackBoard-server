package dev.line4.blackBoard.blackboardsticker.repository;

import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackBoardStickerRepository extends JpaRepository<BlackBoardStickers, Long> {
}
