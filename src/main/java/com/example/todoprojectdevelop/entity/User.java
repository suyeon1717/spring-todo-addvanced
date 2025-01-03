package com.example.todoprojectdevelop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email //import jakarta.validation.constraints.Email;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    // 기본 생성자 필수로 가져야 함
    public User() {
    }

    public User(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public void updateEmail(String userEmail) {
        this.email = userEmail;
    }

    public void updateName(String userName) {
        this.userName = userName;
    }


}
