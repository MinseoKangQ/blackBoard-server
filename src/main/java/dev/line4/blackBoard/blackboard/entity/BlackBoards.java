package dev.line4.blackBoard.blackboard.entity;

import dev.line4.blackBoard.blackboard.dto.CreateBlackBoardDto;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import dev.line4.blackBoard.letter.entity.Letters;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import dev.line4.blackBoard.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
id long
title varchar
introduction varchar
email varchar
open_date localdatetime
url text

*/
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BLACKBOARD")
public class BlackBoards extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 100)
    private String introduction;

    @Column(length = 255)
    private String userId;

    @Column(name = "open_date")
    private String openDate;

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlackBoardStickers> blackBoardStickers = new ArrayList<>(); // 초기화 추가;

    @Builder.Default
    @OneToMany(mappedBy = "blackboard", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Letters> letters = new ArrayList<>();

    // 생성 메서드
    public static BlackBoards createBlackBoard(CreateBlackBoardDto.Req req) {
            return req.getBlackboard().toEntity();
    }

}
