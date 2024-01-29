package dev.line4.blackBoard.blackboard.entity;

import dev.line4.blackBoard.blackboard.dto.AddBlackBoardDto;
import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickerEntity;
import dev.line4.blackBoard.letter.entity.LetterEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import dev.line4.blackBoard.utils.entity.BaseEntity;
import lombok.*;

/*
id long
title varchar
introduction varchar
email varchar
open_date localdatetime
url text

*/
@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BLACKBOARD")
public class BlackBoardEntity extends BaseEntity {

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
    private List<BlackBoardStickerEntity> blackBoardStickers = new ArrayList<>(); // 초기화 추가;

    @Builder.Default
    @OneToMany(mappedBy = "blackboard", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LetterEntity> letters = new ArrayList<>();

    // 생성 메서드
    public static BlackBoardEntity createBlackBoard(AddBlackBoardDto.Req req) {
            return req.getBlackboard().toEntity();
    }

}
