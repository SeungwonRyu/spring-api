package com.example.hanebaapi.loginApp.controller;

import com.example.hanebaapi.loginApp.advice.exception.UserNotFoundException;
import com.example.hanebaapi.loginApp.entity.User;
import com.example.hanebaapi.loginApp.model.response.CommonResult;
import com.example.hanebaapi.loginApp.model.response.ListResult;
import com.example.hanebaapi.loginApp.model.response.SingleResult;
import com.example.hanebaapi.loginApp.repository.UserJpaRepository;
import com.example.hanebaapi.loginApp.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserJpaRepository userJpaRepository;
    private final ResponseService responseService;

    // 유저 조회 (id)
    @GetMapping("/user/id/{id}")
    public SingleResult<User> findUserById(@PathVariable Long id) {
        return responseService.getSingleResult(
                userJpaRepository.findById(id).orElseThrow(UserNotFoundException::new)
        );
    }

    // 유저 조회 (email)
    @GetMapping("/user/email/{email}")
    public SingleResult<User> findUserByEmail(@PathVariable String email) {
        User user = userJpaRepository.findByEmail(email);

        if(user == null) {
            throw new UserNotFoundException();
        } else {
            return responseService.getSingleResult(user);
        }
    }

    // 모든 유저 조회
    @GetMapping("/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userJpaRepository.findAll());
    }

    // 유저 등록
    @PostMapping("/user")
    public SingleResult<User> save(@RequestParam String email, @RequestParam String name) {
        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        return responseService.getSingleResult(userJpaRepository.save(user));
    }

    // 유저 정보 수정
    @PutMapping("/user")
    public SingleResult<User> modify(@RequestParam String email, @RequestParam String name) {
        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        return responseService.getSingleResult(userJpaRepository.save(user));
    }

    // 유저 삭제
    @DeleteMapping("/user/{id}")
    public CommonResult delete(@PathVariable Long id) {
        userJpaRepository.deleteById(id);
        return responseService.getSuccessResult();
    }

    /*
    @GetMapping("/findUserByName/{name}")
    public List<User> findUserByName(@PathVariable String name) {
        return userJpaRepository.findByName(name);
    }

    @GetMapping("/findUserByEmail/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userJpaRepository.findByEmail(email);
    }
    */
}
