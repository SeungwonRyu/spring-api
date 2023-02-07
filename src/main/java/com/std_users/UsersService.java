package com.std_users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRep;

    public List<UsersEntity> getUsersEntity() {
        return usersRep.findAll();
    }
}
