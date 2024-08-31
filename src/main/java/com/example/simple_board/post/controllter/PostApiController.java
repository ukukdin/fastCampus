package com.example.simple_board.post.controllter;

import com.example.simple_board.common.Api;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    @PostMapping("")
    public PostEntity create(
        @Valid
        @RequestBody PostRequest postRequest
    ){
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostEntity view(
            @Valid
       @RequestBody PostViewRequest postViewRequest
            ){
        //orm으로 처리하는 방식이 밑에껏처럼임
//        var entity = postService.view(postViewRequest);
//        entity.getReplyList().forEach(
//                it  -> {
//
//                }
//        );
        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public Api<List<PostEntity>> list(
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC )
        Pageable pageable
    ){
        return postService.all(pageable);
    }

    @PostMapping("/delete")
    public void delete(
        @Valid
        @RequestBody PostViewRequest postViewRequest
    ){
        postService.delete(postViewRequest);
    }
}
