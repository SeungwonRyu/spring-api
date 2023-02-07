package com.example.hanebaapi.loginApp.controller;

import com.example.hanebaapi.loginApp.entity.User;
import com.example.hanebaapi.loginApp.repository.UserJpaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserJpaRepository userJpaRepository;

    @ApiOperation(value = "유저 조회", notes = "모든 유저 조회")
    @GetMapping("/users")
    public List<User> findAllUser() {
        return userJpaRepository.findAll();
    }

    @ApiOperation(value = "유저 등록", notes = "신규 유저 등록")
    @PostMapping("/user")
    public User save(
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "이름", required = true) @RequestParam String name
    ) {
        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        return userJpaRepository.save(user);
    }
}
