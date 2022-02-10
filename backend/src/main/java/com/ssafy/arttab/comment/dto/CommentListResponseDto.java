package com.ssafy.arttab.comment.dto;

import com.ssafy.arttab.comment.Comment;
import com.ssafy.arttab.member.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponseDto {
    private Long id;
    private Long artwork_id;
    private Long member_id;
    private LocalDateTime modifiedDate;
    private String content;
    private Member member;

    public CommentListResponseDto(Comment entity) {
        this.id = entity.getId();
        this.artwork_id = entity.getArtwork().getId();
        this.member_id = entity.getMember().getId();
        this.modifiedDate = entity.getModifiedDate();
        this.content = entity.getContent();
    }
}
