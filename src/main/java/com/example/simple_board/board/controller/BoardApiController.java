package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Validated
public class BoardApiController {

    private final BoardService boardService;
    @PostMapping("")
    public BoardDto create(

        @RequestBody BoardRequest boardRequest
    ){
    return boardService.create(boardRequest);
    }

    @GetMapping("id/{id}")
    public BoardDto view(
            @PathVariable Long id
    ){
        return boardService.view(id);
    }
}
