package com.example.todoprojectdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@Table(name = "todo")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    // 일정 N : 유저 1
    @Setter
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE) //부모 엔티티가 삭제되면 자식 엔티티도 삭제되게 해주는 옵션
    @JoinColumn(name = "user_id")
    private User user;

    // 기본 생성자 필수로 가져야 함
    public Todo() {
    }

    public Todo(String title, String contents) {
        // id는 자동생성, user는 참조하는 값이므로 파라미터 X
        this.title = title;
        this.contents = contents;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
