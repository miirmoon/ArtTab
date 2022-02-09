package com.ssafy.arttab.profile;

import com.ssafy.arttab.artwork.dto.MD5Generator;
import com.ssafy.arttab.exception.member.DuplicateException;
import com.ssafy.arttab.member.service.MemberService;
import com.ssafy.arttab.profile.dto.ProfileInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final MemberService memberService;

    @PostMapping("api/v1/profile")
    public ResponseEntity<String> insertProfile(@RequestPart("file") MultipartFile file,
                                                @RequestParam("email") String email,
                                                @RequestParam(value = "nickname", required = false) String nickname,
                                                @RequestParam(value = "intro", required = false) String intro) throws IOException, NoSuchAlgorithmException {

        // 닉네임 중복 체크
        String message=null;
        if(nickname!=null){ // 수정할 닉네임이 들어올 때에만 체크하기
            try {
                profileService.memberNicknameCheck(nickname);
            }catch (DuplicateException e){
                message= e.getMessage();
            }
        }

        String originFileName=null;
        String saveFileName=null;
        String saveFolder=null;
        int size=0;

        // 프로필 사진 저장하기
        if(!file.isEmpty()) { // 프로필 사진이 설정 안 된 상태로 왔다면
            LocalDateTime time = LocalDateTime.now();
            originFileName = file.getOriginalFilename();
            saveFileName = new MD5Generator(originFileName + time).toString();
            String upperSavePath=System.getProperty("user.dir") + "\\profile"; // 프로필 폴더
            String savePath = upperSavePath + "\\" +email;

            // profile 폴더가 없으면 폴더 생성
            if(!upperSavePath.isEmpty()){
                try{
                    new File(upperSavePath).mkdir();
                } catch(Exception e){
                    e.getStackTrace();
                }
            }

            // profile 폴더 아래에 하나 더 만들기
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            saveFolder = savePath + "\\" + saveFileName;
            file.transferTo(new File(saveFolder)); // 파일 저장

        }

        ProfileInfoDto profileInfo = ProfileInfoDto.builder()
                .email(email)
                .memberNickname(nickname)
                .intro(intro)
                .originFileName(originFileName)
                .saveFileName(saveFileName)
                .saveFolder(saveFolder)
                .size(size)
                .build();

        if(profileService.insertProfile(profileInfo)) { // 입력 완료 되었을 경우
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else { // 입력 실패했을 경우
            return new ResponseEntity<>(FAIL, HttpStatus.OK);
        }

    }

}
