package com.example.todoprojectdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    // 댓글 N : 일정 1
    @Setter
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Column(nullable = false)
    private String contents;

    public Comment() {

    }

    public Comment(String contents) {
        this.contents = contents;
    }

    public void updateContents(String contents){
        this.contents = contents;
    }
}
