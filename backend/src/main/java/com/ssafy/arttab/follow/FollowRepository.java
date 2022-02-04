package com.ssafy.arttab.follow;

import com.ssafy.arttab.comment.Comment;
import com.ssafy.arttab.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Modifying
    @Query(value = "DELETE FROM follow WHERE follower = :follower AND followee = :followee", nativeQuery = true)
    void unFollow(long follower, long followee);
}
