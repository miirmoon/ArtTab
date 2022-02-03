package com.ssafy.arttab.artwork.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;



    @PostMapping("/api/v1/comment")
    public Long insertComment(@RequestBody CommentSaveRequestDto requestDto){
        return commentService.save(requestDto);
    };
}
