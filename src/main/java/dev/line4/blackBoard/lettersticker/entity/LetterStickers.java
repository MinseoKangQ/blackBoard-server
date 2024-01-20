package dev.line4.blackBoard.lettersticker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LETTER_STICKERS")
public class LetterStickers {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LetterStickers that = (LetterStickers) o;
        return Objects.equals(letterStickerId, that.letterStickerId) && Objects.equals(num, that.num)
                && Objects.equals(positionX, that.positionX) && Objects.equals(positionY,
                that.positionY) && Objects.equals(img, that.img) && Objects.equals(width, that.width)
                && Objects.equals(angle, that.angle) && Objects.equals(mirror, that.mirror)
                && Objects.equals(letter, that.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letterStickerId, num, positionX, positionY, img, width, angle, mirror, letter);
    }

    @Override
    public String toString() {
        return "LetterStickers{" +
                "letterStickerId=" + letterStickerId +
                ", num=" + num +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", img=" + img +
                ", width=" + width +
                ", angle=" + angle +
                ", mirror=" + mirror +
                ", letter=" + letter +
                '}';
    }
}