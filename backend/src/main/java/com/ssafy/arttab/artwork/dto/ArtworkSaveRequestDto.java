package com.ssafy.arttab.artwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArtworkSaveRequestDto { // 작품을 저장하는 요청 Request

    private int writerId;
    private String title;
    private String desc;
    private String originFileName;
    private String saveFileName;
    private String saveFolder;
    private int size;
    private double width;
    private double height;

    @Builder
    public ArtworkSaveRequestDto (int writerId, String title, String desc, String originFileName, String saveFileName,
                                  String saveFolder, int size, double width, double height){
        this.writerId=writerId;
        this.title=title;
        this.desc=desc;
        this.originFileName=originFileName;
        this.saveFileName=saveFileName;
        this.saveFolder=saveFolder;
        this.size=size;
        this.width=width;
        this.height=height;
    }
}
