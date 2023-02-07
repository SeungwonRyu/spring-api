package com.example.hanebaapi.login;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/*
* 가입 및 로그인시 사용할 로그인 시간 업데이트
* 이메일로 찾기
*/
public interface UserRepository extends JpaRepository<User, Long> {
    String updateUserLastLoginTime = "UPDATE tbl_user SET last_login_time = NOW() WHERE email = :email";

    @Transactional
    @Modifying
    @Query(value = updateUserLastLoginTime, nativeQuery = true)
    public int updateUserLastLogin(@Param("email") String email);
    public Optional<User> findByEmail(String email);
    public int countByEmailAndDropYn(String email, String dropYn);
}
