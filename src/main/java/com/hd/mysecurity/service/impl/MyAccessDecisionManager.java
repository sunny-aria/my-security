package com.hd.mysecurity.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 描述：决策器,被拦截器调用
 *
 * @author sundf
 * @date 2019-07-23 15:10
 **/
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {


    /**
     * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
     * @param authentication 当前用户，角色的信息
     * @param object  FilterInvocation ，可以获取request信息
     * @param collection  资源角色信息
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if(null==collection || collection.size()<1){
            return;
        }
        for(ConfigAttribute ca:collection){
            String needRole = ca.getAttribute();

            for(GrantedAuthority ga:authentication.getAuthorities()){
               if( ga.getAuthority().trim().equals(needRole.trim())){
                   return;
               }
            }
        }

        throw new AccessDeniedException("当前访问没有权限");
    }

    /**
     * 表示此AccessDecisionManager是否能够处理传递的ConfigAttribute呈现的授权请求
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 表示当前AccessDecisionManager实现是否能够为指定的安全对象（方法调用或Web请求）提供访问控制决策
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
