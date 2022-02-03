package com.ssafy.arttab.artwork.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class CommentSaveRequestDto {
    private String content;
    private Long artwork_id;
    private Long member_id;

    @Builder
    public CommentSaveRequestDto(String content, Long artwork_id, Long member_id) {
        this.content = content;
        this.artwork_id = artwork_id;
        this.member_id = member_id;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
