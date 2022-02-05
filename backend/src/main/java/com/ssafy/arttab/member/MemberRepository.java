package com.ssafy.arttab.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByNickname(String nickname);
//    List<GalleryItem> findGalleryItemById(int id);

    /***
     *
     * @param nickname 회원 목록을 조회할 닉네임
     * @return 닉네임으로 조회한 회원 리스트 반환
     */
    @Query(value = "SELECT * FROM member WHERE nickname LIKE %:nickname%", nativeQuery = true)
    List<Member> findAllByNickname(@Param("nickname") String nickname);
}
