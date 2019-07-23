package com.hd.mysecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 描述：资源，权限
 *
 * @author sundf
 * @date 2019-07-22 18:14
 **/
@Data
@Table
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String name;
    private String description;
    private String pid;

    /**
     * 存放用户的角色信息，一对多，默认情况下是懒加载数据,需指定不许懒加载
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="role_permission" ,
            joinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private List<Role> roleItems;

}
