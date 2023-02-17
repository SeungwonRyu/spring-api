package com.example.hanebaapi.loginApp.controller;

import com.example.hanebaapi.loginApp.domain.dto.user.UserRequestDTO;
import com.example.hanebaapi.loginApp.domain.dto.user.UserResponseDTO;
import com.example.hanebaapi.loginApp.model.response.CommonResult;
import com.example.hanebaapi.loginApp.model.response.ListResult;
import com.example.hanebaapi.loginApp.model.response.SingleResult;
import com.example.hanebaapi.loginApp.service.UserService;
import com.example.hanebaapi.loginApp.service.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@CrossOrigin(originPatterns = "http://localhost:3000")
public class UserController {
    //private final UserJpaRepository userJpaRepository;
    private final UserService userService;
    private final ResponseService responseService;

    // 유저 조회 (id)
    @GetMapping("/user/id/{id}")
    public SingleResult<UserResponseDTO> findUserById(@PathVariable Long id) {
        return responseService.getSingleResult(userService.findById(id));
    }

    // 유저 조회 (email)
    @GetMapping("/user/email/{email}")
    public SingleResult<UserResponseDTO> findUserByEmail(@PathVariable String email) {
        return responseService.getSingleResult(userService.findByEmail(email));
    }

    // 모든 유저 조회
    @GetMapping("/users")
    public ListResult<UserResponseDTO> findAllUser() {
        return responseService.getListResult(userService.findAllUser());
    }

    // 유저 정보 수정
    @PutMapping("/user")
    public SingleResult<Long> update(@RequestParam Long id, @RequestParam String nickname) {
        UserRequestDTO user = UserRequestDTO.builder()
                .nickname(nickname)
                .build();

        return responseService.getSingleResult(userService.update(id, user));
    }

    // 유저 삭제
    @DeleteMapping("/user/{id}")
    public CommonResult delete(@PathVariable Long id) {
        userService.delete(id);
        return responseService.getSuccessResult();
    }
}
