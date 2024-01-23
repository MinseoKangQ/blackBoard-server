package dev.line4.blackBoard.blackboard.repository;

import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackBoardRepository extends JpaRepository<BlackBoards, String> {
    boolean existsByUserId(String userId);
}
