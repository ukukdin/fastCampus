package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name ="board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardName;

    private String status;


    //jpa 로 변경 -> 객체 지향적으로 간다.
    @OneToMany (mappedBy = "board")
    @Where(clause = "status = 'REGISTERED'")
    @Builder.Default
    @org.hibernate.annotations.OrderBy(clause = "id desc")
    private List<PostEntity> postList = List.of();

}
