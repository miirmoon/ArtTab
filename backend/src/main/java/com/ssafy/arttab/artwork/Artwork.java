package com.ssafy.arttab.artwork;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.arttab.BaseTimeEntity;
import com.ssafy.arttab.artwork.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @packageName : com.ssafy.arttab.artwork
 * @fileName : Artwork
 * @date : 2022-02-03
 * @language : JAVA
 * @classification :
 * @time_limit : 2sec
 * @required_time : 00:40 ~ 01:22
 * @submissions : 1
 * @description :
 **/
@Getter
@NoArgsConstructor
@Entity
@Table(name = "artwork")
public class Artwork extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_id")
    private Long id;

    @JsonIgnore //Json으로 변환시 무한루프 방지용
    @OneToMany(mappedBy = "artwork", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

}
