package com.ssafy.arttab.comment;

import com.ssafy.arttab.comment.dto.CommentListResponseDto;
import com.ssafy.arttab.comment.dto.CommentSaveRequestDto;
import com.ssafy.arttab.comment.dto.CommentUpdateRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final CommentService commentService;

    @ApiOperation(value = "댓글 전체 조회",
            notes = "작품번호로 댓글 전체 조회 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @GetMapping("/api/v1/artwork/{id}/comment")
    public ResponseEntity<List<CommentListResponseDto>> selectAllComment(@PathVariable Long id){
        var commentList = commentService.findAllDesc(id);
         if (commentList.isEmpty()){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
        return ResponseEntity.ok().body(commentList);
    }

    @ApiOperation(value = "댓글 등록", notes = "DB에 댓글 등록 ")
    @PostMapping("/api/v1/artwork/{id}/comment")
    public Long insertComment(@PathVariable Long id, @RequestBody CommentSaveRequestDto requestDto){
        return commentService.insert(id, requestDto);
    }

    @PutMapping("/api/v1/artwork/comment/{commentId}")
    public Long updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto) {
        return commentService.update(commentId, requestDto);
    }

    @DeleteMapping("/api/v1/artwork/{id}/comment/{commentId}")
    public Long deleteComment(@PathVariable Long commentId){
        commentService.delete(commentId);
        return commentId;
    }

}
