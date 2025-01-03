package com.example.todoprojectdevelop.repository;

import com.example.todoprojectdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 유저 조회
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameAndEmail(String userName, String email);

    default User findByUserIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exitst id = " + userId));
    }
}
