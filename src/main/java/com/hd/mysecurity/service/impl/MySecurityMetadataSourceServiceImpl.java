package com.hd.mysecurity.service.impl;

import com.hd.mysecurity.dao.PermissionDao;
import com.hd.mysecurity.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 描述： 权限资源，对应的角色，有了它之后，交给AccessDecisionManager 决策器
 *
 * @author sundf
 * @date 2019-07-23 11:38
 **/
@Slf4j
@Component
public class MySecurityMetadataSourceServiceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 每个资源需要的角色， Collection<ConfigAttribute> 决策器会用到
     */
    private static HashMap<String,Collection<ConfigAttribute>> map ;


    /**
     * 返回请求的资源所需要的角色
     * @param o 请求信息
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        if(null == map){
            initPermissionRole();
        }
        // 获取请求
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for(Map.Entry<String,Collection<ConfigAttribute>> entry : map.entrySet()){
            RequestMatcher requestMatcher = new AntPathRequestMatcher(entry.getKey());
            // 匹配请求
            if(requestMatcher.matches(request)){
                //返回权限角色信息
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 初始化资源权限，角色
     */
    public void initPermissionRole(){
        map = new HashMap<>(16);
        List<Permission> list = permissionDao.findAll();
        log.error("permission list.size:"+list.size());
        list.stream().forEach(item->{
            String url = item.getUrl();
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            item.getRoleItems().stream().forEach(role->{
                ConfigAttribute ca = new SecurityConfig(role.getName());
                configAttributes.add(ca);
            });
            // <url,角色信息>，哪个资源可以被什么角色访问
            map.put(url,configAttributes);
        });
    }
}
