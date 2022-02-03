package com.ssafy.arttab.artwork.comment;

import com.ssafy.arttab.artwork.Artwork;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.MemberRemoval;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(CommentSaveRequestDto requestDto) {
        // 작품 번호를 통해 객체 가져오기
//        Artwork artwork = ArtworkRepository.findById(id);
//        Member member = MemberRepository.findById(requestDto.getMember_id());
        return commentRepository.save(requestDto.toEntity()).getId();


    }

}
