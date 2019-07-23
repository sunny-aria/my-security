package com.hd.mysecurity.dao;

import com.hd.mysecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：资源，权限 PermissionDao
 *
 * @author sundf
 * @date 2019-07-23 11:35
 **/
@Repository
public interface PermissionDao extends JpaRepository<Permission,Long> {
}
