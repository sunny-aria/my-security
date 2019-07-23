package com.hd.mysecurity.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * 描述：角色
 *
 * @author sundf
 * @date 2019-07-22 16:12
 **/
@Data
@Table
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //角色名
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
