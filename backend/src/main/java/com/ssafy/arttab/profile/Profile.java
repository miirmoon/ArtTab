package com.ssafy.arttab.profile;

import com.ssafy.arttab.BaseTimeEntity;
import com.ssafy.arttab.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Profile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member member; // 프로필 주인

    @Column
    private String originFileName;

    @Column
    private String saveFolder;

    @Column
    private int size;

    @Builder
    public Profile(Member member, String originFileName, String saveFolder, int size) {
        this.member = member;
        this.originFileName = originFileName;
        this.saveFolder = saveFolder;
        this.size = size;
    }

    // 수정
    public void update(String originFileName, String saveFolder, int size){
        this.originFileName=originFileName;
        this.saveFolder=saveFolder;
        this.size=size;
    }
}
