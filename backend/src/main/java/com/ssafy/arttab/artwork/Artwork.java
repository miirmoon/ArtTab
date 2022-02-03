package com.ssafy.arttab.artwork;

import com.ssafy.arttab.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
@ToString
public class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 작품 식별번호

    @ManyToOne
    private Member writer; // 작성자

    @Column(length = 30)
    private String title; // 작품 이름

    @Column(length = 100)
    private String desc; // 작품 설명

    @CreatedDate
    @Column
    private LocalDateTime regdate; // 작성일

    @Column(length=300)
    private String originFileName; // 원본 파일 이름

    @Column(length=300)
    private String saveFileName; // 서버에 저장된 파일 이름

    @Column(length = 50)
    private String saveFolder; // 저장된 폴더 경로

    @Column
    private int size; // 파일 크기

    @Column
    private double width; // 작품 가로 크기

    @Column
    private double height; // 작품 세로 크기

    @Builder
    public Artwork (Member writer, String title, String desc, String originFileName, String saveFileName,
                    String saveFolder, int size, double width, double height){
        this.writer=writer;
        this.title=title;
        this.desc=desc;
        this.regdate=LocalDateTime.now();
        this.originFileName=originFileName;
        this.saveFileName=saveFileName;
        this.saveFolder=saveFolder;
        this.size=size;
        this.width=width;
        this.height=height;
    }

    public void update(String title, String desc, String originFileName, String saveFileName,
                       String saveFolder, int size, double width, double height){
        this.title=title;
        this.desc=desc;
        this.regdate=LocalDateTime.now();
        this.originFileName=originFileName;
        this.saveFileName=saveFileName;
        this.saveFolder=saveFolder;
        this.size=size;
        this.width=width;
        this.height=height;
    }

}
