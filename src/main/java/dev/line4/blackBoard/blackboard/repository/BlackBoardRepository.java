package dev.line4.blackBoard.blackboard.repository;

import dev.line4.blackBoard.blackboard.entity.BlackBoardEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackBoardRepository extends JpaRepository<BlackBoardEntity, String> {
    Optional<BlackBoardEntity> findBlackBoardsByUserId(String userId);

    boolean existsByUserId(String userId);
}
