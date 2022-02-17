package com.ssafy.arttab.search.dto;

import com.ssafy.arttab.artwork.Artwork;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchArtworkListResponseDto {

    private Long memberId;
    private String memberNickname;
    private Long artworkId; // 작품 식별번호
    private String artworkTitle; // 작품 제목
    private LocalDateTime artworkRegdate; // 작성일
    private String saveFileName; // 서버에 저장된 파일 이름
    private String saveFolder; // 저장된 폴더 경로
    private String imgUrl;
    private boolean likeOrNot;

    public SearchArtworkListResponseDto(Artwork entity, String imgUrl, boolean likeOrNot){
        this.memberId=entity.getWriter().getId();
        this.memberNickname=entity.getWriter().getNickname();
        this.artworkId=entity.getId();
        this.artworkTitle=entity.getTitle();
        this.artworkRegdate=entity.getRegdate();
        this.saveFileName=entity.getSaveFileName();
        this.saveFolder=entity.getSaveFolder();
        this.imgUrl = imgUrl;
        this.likeOrNot = likeOrNot;
    }
}
