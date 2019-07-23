package com.hd.mysecurity.service.impl;

import com.hd.mysecurity.entity.User;
import com.hd.mysecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 描述：实现security 的UserDetailsService接口
 *
 * @author sundf
 * @date 2019-07-23 11:17
 **/

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取该用户的所有信息， 包括用户信息和权限点。
     * @param s 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> op = userService.findUserByUsername(s);
        if(op.isPresent()){
            op.get().getAuthorities();
            return op.get();
        }
        return null;
    }
}
