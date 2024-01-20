package dev.line4.blackBoard.blackboardsticker.entity;

/*
id long
num long
position_x integer
position_y
board_id
 */

import dev.line4.blackBoard.blackboard.entity.BlackBoards;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BLACKBOARD_STICKERS")
public class BlackBoardStickers {
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

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BlackBoards boardId;
}
