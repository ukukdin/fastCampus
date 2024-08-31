package com.example.simple_board.reply.db;


import com.example.simple_board.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name="reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private PostEntity post; // post = > _id => post_id

    private String userName;

    private String password;

    private String status;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "replied_at")
    private LocalDateTime repliedAt;


}
