package dev.line4.blackBoard.blackboardsticker.entity;

import dev.line4.blackBoard.blackboard.dto.CreateBlackBoardDto;
import dev.line4.blackBoard.blackboard.entity.BlackBoards;

import javax.persistence.*;

import dev.line4.blackBoard.utils.entity.BaseEntity;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BLACKBOARD_STICKERS")
public class BlackBoardStickers extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long num;

    @Column(name = "position_x")
    private double positionX;

    @Column(name = "position_y")
    private double positionY;

    @Column(name = "img")
    private Long img;

    @Column(name = "width")
    private Double width;

    @Column(name = "angle")
    private Double angle;

    @Column(name = "mirror")
    private Long mirror;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BlackBoards board;

    // 생성 메서드
    public static BlackBoardStickers createBlackBoardSticker(CreateBlackBoardDto.Req.Sticker req) {
        return req.toEntity();
    }

    // 연관관계 편의 메서드
    public void setBlackBoard(BlackBoards blackBoard) {
        this.board = blackBoard;
        board.getBlackBoardStickers().add(this);
    }

}
