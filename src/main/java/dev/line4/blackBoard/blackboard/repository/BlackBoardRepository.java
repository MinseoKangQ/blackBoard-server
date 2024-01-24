package dev.line4.blackBoard.blackboard.repository;

import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackBoardRepository extends JpaRepository<BlackBoards, String> {
    Optional<BlackBoards> findBlackBoardsByUserId(String userId);

    boolean existsByUserId(String userId);
}
