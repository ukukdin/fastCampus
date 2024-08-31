package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter {
    //데이터를 변환하는 역할만 해줄것이다. 변환하는 코드가 엄청 많아서 컨버터로 다 몰아버린다.
    //postList(boatdentity.getpostlist)를 변경해주기 위해서 참조해야될것도 잇따 포스터컴버터를 만든다.
    //람다익스프레스 변경 알아서 해줌
    /*
    *   boardEntity.getPostList().stream()
                .map(postEntity -> {
                  return  postConverter.toDto(postEntity);
                }).collect(Collectors.toList());
                * */
    private  final PostConverter postConverter;
    public BoardDto toDto(BoardEntity boardEntity){
        var postList = boardEntity.getPostList()
                .stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build()
                ;
    }
}
