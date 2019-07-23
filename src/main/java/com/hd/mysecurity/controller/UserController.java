package com.hd.mysecurity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hd.mysecurity.entity.User;
import com.hd.mysecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 描述： 用户控制类
 *
 * @author sundf
 * @date 2019-07-22 20:49
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/findByName/{username}",method = RequestMethod.GET)
    public String findByName(@PathVariable("username") String username) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<User> optionalUser = userService.findUserByUsername(username);
        User user = new User();
        if(optionalUser.isPresent()){
            user = optionalUser.get();
            user.getAuthorities().stream().forEach(item-> log.info(((GrantedAuthority) item).getAuthority()));
        }
        return objectMapper.writeValueAsString(user);
    }
}
