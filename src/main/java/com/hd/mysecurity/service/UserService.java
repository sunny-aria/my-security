package com.hd.mysecurity.service;

import com.hd.mysecurity.entity.User;

import java.util.Optional;

/**
 * 描述：
 *
 * @author sundf
 * @date 2019-07-22 20:41
 **/
public interface UserService {

    Optional<User>  findUserByUsername(String username);
}
