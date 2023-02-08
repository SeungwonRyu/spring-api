package com.example.hanebaapi.loginApp.service;

import com.example.hanebaapi.loginApp.advice.exception.UserNotFoundException;
import com.example.hanebaapi.loginApp.domain.dto.UserRequestDTO;
import com.example.hanebaapi.loginApp.domain.dto.UserResponseDTO;
import com.example.hanebaapi.loginApp.domain.entity.User;
import com.example.hanebaapi.loginApp.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserJpaRepository userJpaRepository;

    // 유저 등록
    @Transactional
    public Long save(UserRequestDTO userDTO) {
        userJpaRepository.save(userDTO.toEntity());
        return userJpaRepository.findByEmail(userDTO.getEmail()).getId();
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
        User user = userJpaRepository.findByEmail(email);
        if(user == null) {
            throw new UserNotFoundException();
        } else {
            return new UserResponseDTO(user);
        }
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

        //modifiedUser.setNickName(userRequestDTO.getNickName());
        return id;
    }

    // 유저 삭제
    @Transactional
    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }

}
