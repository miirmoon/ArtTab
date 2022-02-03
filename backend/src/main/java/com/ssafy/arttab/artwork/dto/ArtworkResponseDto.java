package com.ssafy.arttab.artwork.dto;

import com.ssafy.arttab.artwork.Artwork;
import lombok.Builder;

import java.time.LocalDateTime;

public class ArtworkResponseDto {

    private int writerId;
    private String title;
    private String desc;
    private LocalDateTime regdate;
    private String originFileName;
    private String saveFileName;
    private String saveFolder;
    private int size;
    private double width;
    private double height;

    @Builder
    public ArtworkResponseDto (Artwork entity){
        this.writerId=entity.getWriter().getId();
        this.title=entity.getTitle();
        this.desc=entity.getDesc();
        this.originFileName=entity.getOriginFileName();
        this.saveFileName=entity.getSaveFileName();
        this.saveFolder=entity.getSaveFolder();
        this.size=entity.getSize();
        this.width=entity.getWidth();
        this.height=entity.getHeight();
    }
}
