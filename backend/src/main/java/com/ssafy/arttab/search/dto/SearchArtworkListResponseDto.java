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
    private int size; // 파일 크기
    private double width; // 작품 가로 크기
    private double height; // 작품 세로 크기

    @Builder
    public SearchArtworkListResponseDto(Artwork entity){
        this.memberId=entity.getWriter().getId();
        this.memberNickname=entity.getWriter().getNickname();
        this.artworkId=entity.getId();
        this.artworkTitle=entity.getTitle();
        this.artworkRegdate=entity.getRegdate();
        this.saveFileName=entity.getSaveFileName();
        this.saveFolder=entity.getSaveFolder();
        this.size=entity.getSize();
        this.width=entity.getWidth();
        this.height=entity.getHeight();
    }
}
