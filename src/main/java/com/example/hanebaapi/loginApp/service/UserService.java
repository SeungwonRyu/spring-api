package com.example.hanebaapi.loginApp.service;

import com.example.hanebaapi.loginApp.advice.exception.EmailLoginFailedException;
import com.example.hanebaapi.loginApp.advice.exception.UserNotFoundException;
import com.example.hanebaapi.loginApp.domain.dto.sign.UserLoginRequestDTO;
import com.example.hanebaapi.loginApp.domain.dto.user.UserRequestDTO;
import com.example.hanebaapi.loginApp.domain.dto.user.UserResponseDTO;
import com.example.hanebaapi.loginApp.domain.entity.User;
import com.example.hanebaapi.loginApp.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UserJpaRepository userJpaRepository;

    // 유저 등록
    @Transactional
    public Long save(UserRequestDTO userDTO) {
        User saved = userJpaRepository.save(userDTO.toEntity());
        return saved.getId();
    }

    // ID로 조회
    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User user = userJpaRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponseDTO(user);
    }

    // 이메일로 조회
    @Transactional(readOnly = true)
    public UserResponseDTO findByEmail(String email) {
        User user = userJpaRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponseDTO(user);
    }

    // 모든 유저 조회
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllUser() {
        return userJpaRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 유저 정보 수정
    @Transactional
    public Long update(Long id, UserRequestDTO userRequestDTO) {
        User modifiedUser = userJpaRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        modifiedUser.updateNickName(userRequestDTO.getNickname());
        return id;
    }

    // 유저 삭제
    @Transactional
    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }
}
