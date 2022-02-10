package com.ssafy.arttab.artwork.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// 회원이 팔로우하는 회원들의 작품 리스트를 리턴하기 위한 dto
@Setter
@NoArgsConstructor
public class FollowArtworkListResponseDto {

    private Long followMemberId; // 회원 아이디
    private String followMemberNickname; // 회원 닉네임
    private LocalDateTime latestRegDate; // 가장 최근에 업로드한 작품의 작성일
    private String saveFolder; // 서버에 저장된 프로필 사진 경로
    private int artworkNum; // 회원의 작품 수
    private int followerNum; // 회원을 팔로우하는 사람 수
    private List<SimpleArtworkDto> artworkList; // 회원이 최근에 그린 작품들

    @Builder
    public FollowArtworkListResponseDto(Long followMemberId, String followMemberNickname, LocalDateTime latestRegDate, String saveFolder, int artworkNum, int followerNum, List<SimpleArtworkDto> artworkList) {
        this.followMemberId = followMemberId;
        this.followMemberNickname = followMemberNickname;
        this.latestRegDate = latestRegDate;
        this.saveFolder = saveFolder;
        this.artworkNum = artworkNum;
        this.followerNum = followerNum;
        this.artworkList = artworkList;
    }
}
