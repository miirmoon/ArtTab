package com.ssafy.arttab.profile;

import com.ssafy.arttab.exception.member.DuplicateException;
import com.ssafy.arttab.exception.member.NoSuchMemberException;
import com.ssafy.arttab.member.domain.Member;
import com.ssafy.arttab.member.repository.MemberRepository;
import com.ssafy.arttab.profile.dto.ProfileInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    // 닉네임 중복 검사
    @Transactional(readOnly = true)
    public void memberNicknameCheck(final String nickname){
        if(memberRepository.existsMembersByNickname(nickname)){
            throw new DuplicateException();
        }
    }

    // 프로필 추가
    @Transactional
    public boolean insertProfile(ProfileInfoDto profileInfo){

        var member = memberRepository.findByEmail(profileInfo.getEmail())
                .orElseThrow(NoSuchMemberException::new); // 이메일에 해당하는 회원 가져온다

        if(profileInfo.getMemberNickname()!=null) { // 수정하고 싶은 닉네임을 입력했을 경우
            member.updateNickname(profileInfo.getMemberNickname()); // 멤버의 닉네임 수정해주기
        }
        if(profileInfo.getIntro()!=null) { // 수정하고 싶은 자기소개를 입력했을 경우
            member.updateIntro(profileInfo.getIntro()); // 멤버의 자기소개 수정해주기
        }
        // 프로필 정보 추가해주기
        Profile profile = profileRepository.save(Profile.builder()
                .member(member)
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
