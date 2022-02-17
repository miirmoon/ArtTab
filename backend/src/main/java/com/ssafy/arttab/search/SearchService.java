package com.ssafy.arttab.search;


import com.ssafy.arttab.artwork.Artwork;
import com.ssafy.arttab.artwork.ArtworkRepository;
import com.ssafy.arttab.follow.FollowRepository;
import com.ssafy.arttab.member.domain.Member;
import com.ssafy.arttab.member.repository.MemberRepository;
import com.ssafy.arttab.search.dto.SearchArtworkListResponseDto;
import com.ssafy.arttab.search.dto.SearchMemberListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final ArtworkRepository artworkRepository;
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Value("${access.url.artworks}")
    private String artworkImgUrl;

    @Transactional
    public List<SearchArtworkListResponseDto> selectArtworkList(String title){
        List<Artwork> artworkList = artworkRepository.findAllByTitle(title);
        List<SearchArtworkListResponseDto> responseDtos = new ArrayList<>();

        for (Artwork artwork : artworkList) {
            String imgUrl = artworkImgUrl + artwork.getSaveFileName();
            responseDtos.add(new SearchArtworkListResponseDto(artwork, imgUrl));
        }
        return responseDtos;
    }

    @Transactional
    public List<SearchMemberListResponseDto> selectMemberList(String nickname){
        List<Member> memberdtoList = memberRepository.findAllByNickname(nickname);
        List<SearchMemberListResponseDto> responseDtos = new ArrayList<>();

        for (Member memberdto : memberdtoList) {
            List<Artwork> artworkList = artworkRepository.findAllByMemberId(memberdto.getId()) ;
            int artworkCnt = artworkList.size();
            int followCnt = followRepository.findAllFollowCnt(memberdto.getId());
            responseDtos.add(new SearchMemberListResponseDto(memberdto.getNickname(), artworkList, artworkCnt, followCnt));
        }

        return responseDtos;

    }
}
