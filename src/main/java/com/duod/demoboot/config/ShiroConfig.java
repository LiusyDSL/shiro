package com.duod.demoboot.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("执行 ShiroFilterFactoryBean.shiroFilter()...");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置登录的接口，如果访问那个接口，需要登录没有登录，则调用此接口(如果不是前后端分离，则填写调转的页面)
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");

        //登录成功跳转的url,如果前后端分离，则没用这个
        shiroFilterFactoryBean.setSuccessUrl("/");

        //没有权限，未授权就是会调用此方法。   先验证登录 ---> 在验证是否有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");

        //设置拦截路径
        // 坑：部分路径无法拦|截，时有时无，因为用的是hashMap，无序的，应该使用LinKedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //退出过滤
        filterChainDefinitionMap.put("/logout","logout");

        //匿名访问，也就是游客访问
        filterChainDefinitionMap.put("/pub/**","anon");

        //登录才可以访问
        filterChainDefinitionMap.put("/authc/**","authc");

        //管理员角色才可以访问
        filterChainDefinitionMap.put("/admin/**","roles[admin]");

        //有编辑权限才可以访问
        filterChainDefinitionMap.put("/video/update","perms[video_update]");

        //坑：过滤器链是按顺序执行，从上而下，一般/** 放在最后
        //authc: url必须通过认证才能访问
        //anon: url可以匿名访问
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;

    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //如果不是前后端分离，则不必设置下面的sessionManager
        securityManager.setSessionManager(sessionManager());
        //不要使用 new customRealm()  [推荐放到最后，不然某些情况下不生效]
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    //密码机密
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        //设置散列算法，这里使用MD5算法
        credentialsMatcher.setHashAlgorithmName("md5");

        //设置散列次数，好比散列2次，相当于MD5(MD5(password))
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }


    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();

        //超时时间，默认为30分钟  方法里的单位为毫秒
        customSessionManager.setGlobalSessionTimeout(20000);
        return customSessionManager;
    }

}
