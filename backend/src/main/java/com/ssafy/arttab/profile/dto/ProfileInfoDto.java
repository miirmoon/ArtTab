package com.ssafy.arttab.profile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileInfoDto {

    private String memberNickname; // 프로필 사진 주인 닉네임
    private String originFileName; // 원본 파일 이름
    private String saveFileName; // 저장할 파일 이름
    private String saveFolder; // 저장할 폴더 경로
    private int size; // 파일 크기

    @Builder
    public ProfileInfoDto(String memberNickname, String originFileName, String saveFileName, String saveFolder, int size) {
        this.memberNickname = memberNickname;
        this.originFileName = originFileName;
        this.saveFileName = saveFileName;
        this.saveFolder = saveFolder;
        this.size = size;
    }
}
