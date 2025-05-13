package com.example.simple_board.post.model;

import com.example.simple_board.board.db.BoardEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId; //시스템상 board+_id -> board_id 가 된다.

    private String user_name;

    private String password;

    private String email;

    private String status;

    private String title;

    private String content;

    private LocalDateTime postedAt;

}
