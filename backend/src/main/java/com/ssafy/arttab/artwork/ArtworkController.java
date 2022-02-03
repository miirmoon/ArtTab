package com.ssafy.arttab.artwork;

import com.ssafy.arttab.artwork.dto.ArtworkSaveRequestDto;
import com.ssafy.arttab.artwork.dto.ArtworkUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RequestMapping("/artwork")
@RestController
@RequiredArgsConstructor
public class ArtworkController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private final ArtworkService artworkService;

    // 작품 등록 api
    @PostMapping("api/v1/artwork")
    public ResponseEntity<String> insert(@RequestBody ArtworkSaveRequestDto request) {
        boolean result = artworkService.save(request);

        // save에서 결과가 없는 경우 처리해야 함
        if(!result) // result가 false인 경우, 저장 실패한 것임
            return new ResponseEntity<>(FAIL, HttpStatus.OK);

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    // 작품 수정 api
    @PutMapping("api/v1/artwork/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody ArtworkUpdateRequestDto artwork){

        Optional<Artwork> artworkResult=artworkService.update(id, artwork);

        if(artworkResult.isEmpty())
            return new ResponseEntity<>(FAIL, HttpStatus.OK);

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    // 작품 삭제 api
    @DeleteMapping("api/v1/artwork/{no}")
    public void deleteArtwork(@PathVariable("no") int no){
        artworkService.delete(no);
    }


}
