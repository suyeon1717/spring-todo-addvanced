package com.example.todoprojectdevelop.repository;

import com.example.todoprojectdevelop.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 유저 조회
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameAndEmail(String userName, String email);

}
