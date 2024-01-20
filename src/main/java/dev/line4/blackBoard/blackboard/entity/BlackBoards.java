package dev.line4.blackBoard.blackboard.entity;

import dev.line4.blackBoard.blackboardsticker.entity.BlackBoardStickers;
import dev.line4.blackBoard.letter.entity.Letters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
graduate_date localdatetime
url text

*/
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BLACKBOARD")
public class BlackBoards {
    @Id
    private String id;
    @Column(length = 50)
    private String title;
    @Column(length = 100)
    private String introduction;
    @Column(length = 255)
    private String email;
    @Column(name = "graduate_date")
    private String graduateDate;
    @OneToMany(mappedBy = "boardId", fetch = FetchType.LAZY)
    private Set<BlackBoardStickers> blackBoardStickers;
    @Builder.Default
    @OneToMany(mappedBy = "blackboard", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Letters> letters = new ArrayList<>();
}
