package dev.line4.blackBoard.lettersticker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.line4.blackBoard.letter.dto.CreateLetterDto;
import dev.line4.blackBoard.letter.entity.Letters;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dev.line4.blackBoard.utils.entity.BaseEntity;
import lombok.*;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LETTER_STICKERS")
public class LetterStickers extends BaseEntity {

    @Id
    @Column(name = "letter_sticker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterStickerId;

    @Column(name = "num")
    private Long num;

    @Column(name = "position_x")
    private Double positionX;

    @Column(name = "position_y")
    private Double positionY;

    @Column(name = "img")
    private Long img;

    @Column(name = "width")
    private Double width;

    @Column(name = "angle")
    private Double angle;

    @Column(name = "mirror")
    private Long mirror;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_id")
    private Letters letter;

    // 생성 메서드
    public static LetterStickers createLetterSticker(CreateLetterDto.Req.Sticker dto) {
        return dto.toEntity();
    }
}