package com.hd.mysecurity.dao;

import com.hd.mysecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：RoleDao 角色
 *
 * @author sundf
 * @date 2019-07-22 18:11
 **/
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
}
