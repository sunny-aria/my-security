package com.hd.mysecurity.dao;

import com.hd.mysecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：userDao
 *
 * @author sundf
 * @date 2019-07-22 18:08
 **/
@Repository
public interface UserDao extends JpaRepository<User,Long> {


    User findUserByUsername(String username);
}
