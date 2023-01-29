package com.example.hanebaapi.std_users;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UsersController {
    @Autowired
    UsersService usersServ;

    // 조회
    //@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @GetMapping("users")
    public ResponseEntity<List<UsersEntity>> getUsersEntity() {
        List<UsersEntity> users = usersServ.getUsersEntity();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}