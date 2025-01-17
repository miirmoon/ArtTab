package com.ssafy.arttab.member.service;

import com.ssafy.arttab.artwork.ArtworkRepository;
import com.ssafy.arttab.config.JWTUtil;
import com.ssafy.arttab.exception.authorization.NoauthorizedMemberException;
import com.ssafy.arttab.exception.member.DuplicateException;
import com.ssafy.arttab.exception.member.NoSuchMemberException;
import com.ssafy.arttab.exception.member.PasswordMismatchException;
import com.ssafy.arttab.follow.FollowRepository;
import com.ssafy.arttab.member.domain.MailAuth;
import com.ssafy.arttab.member.domain.Member;
import com.ssafy.arttab.member.dto.LoginEmail;
import com.ssafy.arttab.member.dto.User;
import com.ssafy.arttab.member.dto.request.AuthNumCheckRequest;
import com.ssafy.arttab.member.dto.request.IntroUpdateRequest;
import com.ssafy.arttab.member.dto.request.MemberSaveRequest;
import com.ssafy.arttab.member.dto.request.PasswordUpdateRequest;
import com.ssafy.arttab.member.dto.response.MemberInfoResponse;
import com.ssafy.arttab.member.dto.response.ProfileInfoResponse;
import com.ssafy.arttab.member.repository.MailAuthRepogitory;
import com.ssafy.arttab.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

/**
 * @packageName : com.ssafy.arttab.member
 * @fileName : MemberService
 * @date : 2022-02-03
 * @language : JAVA
 * @classification :
 * @time_limit : 2sec
 * @required_time : 00:40 ~ 01:22
 * @submissions : 1
 * @description :
 **/

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final MailSendService mailSendService;
    private final MailAuthRepogitory mailAuthRepogitory;
    private final FollowRepository followRepository;
    private final ArtworkRepository artworkRepository;

//    @Value("${access.url.artworks}")
//    private String artworkImgUrl;

    @Value("${access.url.location}")
    private String location;

    @Value("${access.url.profiles}")
    private String profileImgUrl;

    @Value("${access.url.profileDefault}")
    private String profileDefaultImgUrl;

    /**
     * 회원 등록
     * @param memberSaveRequest
     * @return
     */
    @Transactional
    public boolean saveMember(final MemberSaveRequest memberSaveRequest){
        //중복검사
        MemberEmailCheck(memberSaveRequest.getEmail());


        //비밀번호 암호화
        var password = BCrypt.hashpw(memberSaveRequest.getPassword(),BCrypt.gensalt());

        // 프로필 사진 기본 이미지로 설정
        String defaultSaveFolder = "";
        if ("dev".equals(location)){
             defaultSaveFolder=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"default.jpg";
        } else if ("ec2".equals(location)) {
            defaultSaveFolder=System.getProperty("user.dir")+"img"+ File.separator + "default.jpg";
        }


        Member member = Member.builder()
                .email(memberSaveRequest.getEmail())
                .password(password)
                .saveFolder(defaultSaveFolder)
                .saveFilename("default.jpg")
                .build();

        try{
            memberRepository.save(member);
            //인증 메일 보내기
            SendNumtoEmail(member.getEmail());
            return true;
        }catch (Exception e){
            return false;
        }



    }

    /**
     * 닉네임 중복 검사
     * @param nickname
     * @return
     */
    @Transactional(readOnly = true)
    public void MemberIdCheck(final String nickname){
        if(memberRepository.existsMembersByNickname(nickname)){
            throw new DuplicateException();
        }

    }

    /**
     * 이메일 중복검사
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public void MemberEmailCheck(final String email){
        if(memberRepository.existsMembersByEmail(email)){
            throw new DuplicateException();
        }

    }

    /**
     * 이메일로 인증번호 보내기
     * @param email
     * @return
     */
    public void SendNumtoEmail(final String email){
        // 인증번호 생성
        final String pwd = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);


        // DB 확인
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(NoSuchMemberException::new);

        var mailAuth = mailAuthRepogitory.findById(member.getId());


        // DB에 있으면 변경, 없으면 등록
        mailAuth.ifPresentOrElse(selectmailAuth ->{
                    selectmailAuth.setId(pwd);
                    mailAuthRepogitory.save(selectmailAuth);
                },() -> {
            var mailAuth1 = MailAuth.builder()
                    .id(pwd)
                    .memNo(member.getId())
                    .build();
            mailAuthRepogitory.save(mailAuth1);}


        );

        // 이메일보내기
        HashMap values = new HashMap<String,String>();
        values.put("password",pwd);
        try {
            mailSendService.sendEmail(email, "가입해주셔서  감사합니다 인증번호를 발급해 드립니다","welcome",values);

        }catch (Exception e){
            throw new NoauthorizedMemberException();
        }
    }
    public void selectMailAuthId(final AuthNumCheckRequest authNumCheckRequest){
        //이메일로 Id찾기
        var member = memberRepository.findByEmail(authNumCheckRequest.getEmail())
                .orElseThrow(NoSuchMemberException::new);

        var mailAuth = mailAuthRepogitory.findById(member.getId())
                .orElseThrow(NoSuchMemberException::new);

        //인증번호 맞으면 권한 바꾸기기
       if(mailAuth.getId().equals(authNumCheckRequest.getId())) {
           member.updateAuth();
           mailAuthRepogitory.delete(mailAuth);
       }else{
           throw new PasswordMismatchException();
       }
    }

    /**
     * 회원 로그인
     * @param user
     */
    public String login(final User user){
        Member member = memberRepository.findByEmail(user.getEmail())
                .orElseThrow(NoSuchMemberException::new);

        if (!BCrypt.checkpw(user.getPassword(), member.getPassword())) {
            throw new PasswordMismatchException("passwordMismatch");
        }
        if(member.getAuth()!=1){
            throw new NoauthorizedMemberException();
        }
        //토큰 발급
        HashMap<String, Object> payload = new HashMap();
        payload.put("Id",member.getId());
        return jwtUtil.createToken(payload);
    }
    /**
     * 닉네임 등록
     * @param loginEmail
     * @param nickname
     */
    @Transactional
    public void addNickname(final LoginEmail loginEmail,final String nickname ){
        var member = memberRepository.findByEmail(loginEmail.getEmail())
                .orElseThrow(NoSuchMemberException::new);

        member.updateNickname(nickname);
    }
    /**
     * 회원 조회
     * @param loginEmail
     * @return
     */
    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(final LoginEmail loginEmail){
        var member = memberRepository.findByEmail(loginEmail.getEmail())
                .orElseThrow(NoSuchMemberException::new);
        var memberInfoResponse = MemberInfoResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .build();
        return memberInfoResponse;
    }

    /**
     * 비밀번호 수정
     * @param
     * @param passwordUpdateRequest
     */
    @Transactional
    public void updatePassword(final PasswordUpdateRequest passwordUpdateRequest){
        var member = memberRepository.findByEmail(passwordUpdateRequest.getLoginEmail())
                .orElseThrow(NoSuchMemberException::new);
        if(BCrypt.checkpw(passwordUpdateRequest.getPassword(),member.getPassword())){
            member.updatepassword(BCrypt.hashpw(passwordUpdateRequest.getNewPassword(),BCrypt.gensalt()));
        }else{
            throw new PasswordMismatchException();
        }

    }

    /**
     * 비밀번호 찾기
     * @param email
     * @param
     */
    public void findPassword(final String email){
        var member = memberRepository.findByEmail(email)
                .orElseThrow(NoSuchMemberException::new);

        // 인증번호 생성
        final String pwd = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        // DB 변경
        member.updatepassword(pwd);

        //이메일 보내기
        StringBuilder cntnt = new StringBuilder();

        HashMap values = new HashMap();
        values.put("password",pwd);
        mailSendService.sendEmail(email, "안녕하세요.Art Tab입니다." +member.getNickname()+"님의 임시 비밀번호 입니다.", "findpassword",values);

    }

   /**
     * 소개글 수정
     * @param loginEmail
     * @param memberUpdateRequest
     */
    @Transactional
    public void updateMember(final LoginEmail loginEmail,final IntroUpdateRequest memberUpdateRequest){
        var member = memberRepository.findByEmail(loginEmail.getEmail())
                .orElseThrow(NoSuchMemberException::new);

        member.updateIntro(memberUpdateRequest.getIntro());
    }

    /**
     * 회원 삭제
     * @param id
     */
    @Transactional
    public void deleteMember(final Long id){
        var member = memberRepository.findById(id)
                .orElseThrow(NoSuchMemberException::new);

        memberRepository.delete(member);
    }

    // 해당 이메일의 회원이 기존에 등록해둔 saveFolder 리턴
    public String getParentFolder(String email){

        var member=memberRepository.findByEmail(email).orElseThrow();
        return member.getSaveFolder();

    }

    // saveFolder 수정: 이메일에 해당하는 프로필 사진 수정
    @Transactional
    public void updateSaveFolder(final LoginEmail loginEmail, String saveFolder){
        var member=memberRepository.findByEmail(loginEmail.getEmail()).orElseThrow();
        member.updateSaveFolder(saveFolder);
    }

    // 프로필 페이지 불러오기
    public ProfileInfoResponse getProfileInfo(Long loginId, Long profileMemberId){

//        Long loginMemberId=memberRepository.findById(loginId).get().getId(); // 로그인된 회원 아이디
//        Long profileMember=memberRepository.findById(profileMemberId).get().getId(); // 프로필을 주인 아이디

        Member loginMember=memberRepository.findById(loginId).get();
        Member profileMember=memberRepository.findById(profileMemberId).get();

        String isFollow = "FALSE";
        if(loginMember.getId()==profileMember.getId()) { // 로그인한 사용자가 본인 프로필 조회하려고 할 때
            isFollow="ME";
        }
        else {
            int followOrNot = followRepository.isFollow(loginMember.getId(), profileMember.getId());
            if (followOrNot != 0) { // 팔로우 관계일 때
                isFollow = "TRUE";
            }
        }

        String defaultProfileImgUrl=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"default.jpg";
        String profileImg=profileMember.getSaveFolder();

        String profileImageUrl=profileDefaultImgUrl+profileMember.getSaveFilename(); // 프로필 사진 url
        if(!profileImg.equals(defaultProfileImgUrl)){ // 프로필 이미지가 기본 프로필 이미지 상태가 아닐 때
            profileImageUrl=profileImgUrl+memberRepository.findById(profileMemberId).get().getSaveFilename();
        }

        ProfileInfoResponse response = ProfileInfoResponse.builder()
                .nickname(memberRepository.findById(profileMemberId).get().getNickname())
                .isFollow(isFollow)
                .followedNum(followRepository.findAllFollowedCnt(profileMember.getId()))
                .followingNum(followRepository.findAllFollowingCnt(profileMember.getId()))
                .artworkNum(artworkRepository.findNumByMemberId(profileMember.getId()))
                .email(memberRepository.findById(profileMemberId).get().getEmail())
                .profileImageUrl(profileImageUrl)
                .intro(profileMember.getIntro())
                .build();

        return response;
    }

    public void updateSaveFilename(LoginEmail loginEmail, String saveFileName) {
        var member=memberRepository.findByEmail(loginEmail.getEmail()).orElseThrow();
        member.updateSaveFileName(saveFileName);
    }
}
