package com.example.todoprojectdevelop.service;

import com.example.todoprojectdevelop.config.PasswordEncoder;
import com.example.todoprojectdevelop.dto.SignUpResponseDto;
import com.example.todoprojectdevelop.dto.UserResponseDto;
import com.example.todoprojectdevelop.entity.User;
import com.example.todoprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcrypt;

    // 유저 생성
    public SignUpResponseDto signUp(String email, String password,  String userName) {

        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
        User user = new User(email, password, userName);
        User savedUser = userRepository.save(user); // Repository에 저장

        return new SignUpResponseDto(savedUser);
    }

    // 전체 유저 조회
    public List<UserResponseDto> findAll(String userName, String email) {

        List<User> userList = null;

        if(userName == null && email == null) {
            userList = userRepository.findAll();
        } else if(userName == null) {
            User findUser = userRepository.findByEmail(email);
            userList.add(findUser);
//            userList.add(userRepository.findByEmail(email));
        } else if(email == null) {
            userList = userRepository.findByUserName(userName);
        } else {
            userList = userRepository.findByUserNameAndEmail(userName, email);
        }

        // NPE 방지
        if (userList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<UserResponseDto> userResponseDtoList =
                userList.stream()
                        .map(user -> new UserResponseDto(user))
                        .collect(Collectors.toList());

        return userResponseDtoList;
    }

    // 특정 유저 조회
    public UserResponseDto findByUserId(Long userId) {
        User user = userRepository.findByUserIdOrElseThrow(userId);
        return new UserResponseDto(user);
    }

    // 유저 수정
    @Transactional
    public UserResponseDto updateUser(Long userId, String userEmail, String userName) {
        User user = userRepository.findByUserIdOrElseThrow(userId);

        // 이메일 수정
        if(userEmail != null) {
            user.updateEmail(userEmail);
        }

        // 이름 수정
        if(userName != null) {
            user.updateName(userName);
        }
        userRepository.flush();

        return new UserResponseDto(user);
    }

    // 유저 삭제
    public void deleteUser(Long userId) {
        User user = userRepository.findByUserIdOrElseThrow(userId);

        userRepository.delete(user);
    }

    // 유저 로그인
    public Boolean login(String email, String password) {
        // DB에 저장된 인코딩된 비밀번호를 가져옴
        String encodedPassword = userRepository.findByEmail(email).getPassword();
        // 비밀번호가 일치하면 true
        return bcrypt.matches(password, encodedPassword);
    }

    // 이메일로 유저 조회
    public UserResponseDto findByEmail(String email) {
        User findUser = userRepository.findByEmail(email);
        return new UserResponseDto(findUser);
    }


}
