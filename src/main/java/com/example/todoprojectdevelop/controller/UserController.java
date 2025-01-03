package com.example.todoprojectdevelop.controller;

import com.example.todoprojectdevelop.config.PasswordEncoder;
import com.example.todoprojectdevelop.dto.SignUpRequestDto;
import com.example.todoprojectdevelop.dto.SignUpResponseDto;
import com.example.todoprojectdevelop.dto.UserResponseDto;
import com.example.todoprojectdevelop.dto.UserUpdateRequestDto;
import com.example.todoprojectdevelop.repository.UserRepository;
import com.example.todoprojectdevelop.service.UserService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder bcrypt;

    // 유저 생성
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = userService.signUp(
                requestDto.getEmail(),
                bcrypt.encode(requestDto.getPassword()),
                requestDto.getUserName()
        );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(
            @RequestParam (required = false) String userName,
            @Email @RequestParam (required = false) String email
    ) {
        List<UserResponseDto> userResponseDtoList = userService.findAll(userName, email);

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    // 특정 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findByUserId(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.findByUserId(userId);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequestDto requestDto
            ) {
        UserResponseDto userResponseDto = userService.updateUser(userId, requestDto.getEmail(), requestDto.getUserName());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
