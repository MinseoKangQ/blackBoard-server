package dev.line4.blackBoard.letter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.line4.blackBoard.blackboard.entity.BlackBoardEntity;
import dev.line4.blackBoard.letter.dto.AddLetterDto;
import dev.line4.blackBoard.lettersticker.entity.LetterStickerEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dev.line4.blackBoard.utils.entity.BaseEntity;
import lombok.*;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LETTER")
public class LetterEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "font")
    private String font;

    @Column(name = "align")
    private String align;

    @JsonIgnore
    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BlackBoardEntity blackboard; // FK

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "letter", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LetterStickerEntity> letterStickers = new ArrayList<>();

    // 생성 메서드
    public static LetterEntity createLetter(AddLetterDto.Req.Letter req) {
        return req.toEntity();
    }

    // 연관관계 메서드
    public void setBlackBoard(BlackBoardEntity blackBoard) {
        this.blackboard = blackBoard;
        blackBoard.getLetters().add(this);
    }
}
