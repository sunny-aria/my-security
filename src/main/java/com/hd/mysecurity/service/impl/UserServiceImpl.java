package com.hd.mysecurity.service.impl;

import com.hd.mysecurity.dao.UserDao;
import com.hd.mysecurity.entity.User;
import com.hd.mysecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 描述：
 *
 * @author sundf
 * @date 2019-07-22 20:45
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userDao.findUserByUsername(username));
    }
}
