package dev.line4.blackBoard.letter.repository;

import dev.line4.blackBoard.letter.entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<LetterEntity, Long> {
}
