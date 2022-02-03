package com.ssafy.arttab.artwork;

import com.ssafy.arttab.artwork.dto.ArtworkListResponseDto;
import com.ssafy.arttab.artwork.dto.ArtworkSaveRequestDto;
import com.ssafy.arttab.artwork.dto.ArtworkUpdateRequestDto;
import com.ssafy.arttab.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean save(ArtworkSaveRequestDto requestDto) {
        Artwork artwork = artworkRepository.save(
                Artwork.builder()
                        .writer(memberRepository.findById(requestDto.getWriterId()).get())
                        .title(requestDto.getTitle())
                        .desc(requestDto.getDesc())
                        .originFileName(requestDto.getOriginFileName())
                        .saveFileName(requestDto.getSaveFileName())
                        .saveFolder(requestDto.getSaveFolder())
                        .size(requestDto.getSize())
                        .width(requestDto.getWidth())
                        .height(requestDto.getHeight())
                        .build()
        );

        if(artwork==null) return false; // 삽입 실패

        return true;
    }

    @Transactional
    public Optional<Artwork> update(int id, ArtworkUpdateRequestDto requestDto){
        Optional<Artwork> artwork = artworkRepository.findById(id); // 수정할 작품 찾기
        artwork.get().update(requestDto.getTitle(), requestDto.getDesc(), requestDto.getOriginFileName(),
                requestDto.getSaveFileName(), requestDto.getSaveFolder(), requestDto.getSize(),
                requestDto.getWidth(), requestDto.getHeight());
        return artwork;
    }

    public Optional<Artwork> findByNo(int no){
        Optional<Artwork> entity=artworkRepository.findById(no);

        return entity;
    }

    @Transactional
    public void delete(int id){
        Artwork artwork=artworkRepository.findById(id).get();
        artworkRepository.delete(artwork);
    }

}
