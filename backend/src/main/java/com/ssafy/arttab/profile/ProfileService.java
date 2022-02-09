package com.ssafy.arttab.profile;

import com.ssafy.arttab.member.repository.MemberRepository;
import com.ssafy.arttab.profile.dto.ProfileInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    // 프로필 추가
    public boolean insertProfile(ProfileInfoDto profileInfo){
        Profile profile = profileRepository.save(Profile.builder()
                .member(memberRepository.findMemberByNickname(profileInfo.getMemberNickname()))
                .originFileName(profileInfo.getOriginFileName())
                .saveFolder(profileInfo.getSaveFolder())
                .size(profileInfo.getSize())
                .build()
        );

        if(profile == null) return false;

        return true;
    }

    // 프로필 수정
    public Profile updateProfile(ProfileInfoDto profileInfoDto){

        Profile profile = memberRepository.findMemberByNickname(profileInfoDto.getMemberNickname()).getProfile(); // 수정할 프로필
        profile.update(profileInfoDto.getOriginFileName(), profileInfoDto.getSaveFolder(), profileInfoDto.getSize());
        return profile;
    }
}
