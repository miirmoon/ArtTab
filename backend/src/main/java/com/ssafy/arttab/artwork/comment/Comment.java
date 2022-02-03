package com.ssafy.arttab.artwork.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.arttab.artwork.Artwork;
import com.ssafy.arttab.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Artwork artwork;

    // 회원 FK
//    @OneToOne
//    @JoinColumn(name = "member_no")
//    private Member member_id;

    @Builder
    public Comment(Long id, String content ){
        this.id = id;
        this.content = content;
    }

}
