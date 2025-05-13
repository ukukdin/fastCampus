package com.example.simple_board.post.service;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostConverter {

    public PostDto toDto(PostEntity postEntity){
         return PostDto.builder()
            .id(postEntity.getId())
            .user_name(postEntity.getUser_name())
            .status(postEntity.getStatus())
            .email(postEntity.getEmail())
            .password(postEntity.getPassword())
            .title(postEntity.getTitle())
            .content(postEntity.getContent())
            .postedAt(postEntity.getPostedAt())
            .boardId(postEntity.getBoard().getId())
            .build()
                ;
    }
}
