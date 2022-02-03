package com.ssafy.arttab.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByNickname(String nickname);
//    List<GalleryItem> findGalleryItemById(int id);
}
