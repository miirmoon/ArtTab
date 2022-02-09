package com.ssafy.arttab.profile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileInfoDto {

    private String email; // 프로필 사진 주인 이메일
    private String memberNickname; // 프로필 사진 주인 닉네임
    private String intro; // 자기소개
    private String originFileName; // 원본 파일 이름
    private String saveFileName; // 저장할 파일 이름
    private String saveFolder; // 저장할 폴더 경로
    private int size; // 파일 크기

    @Builder
    public ProfileInfoDto(String email, String memberNickname, String intro, String originFileName, String saveFileName, String saveFolder, int size) {
        this.email=email;
        this.memberNickname = memberNickname;
        this.intro=intro;
        this.originFileName = originFileName;
        this.saveFileName = saveFileName;
        this.saveFolder = saveFolder;
        this.size = size;
    }
}
