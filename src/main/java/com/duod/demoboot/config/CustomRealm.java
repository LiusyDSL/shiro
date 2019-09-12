package com.duod.demoboot.config;

import com.duod.demoboot.domain.Permission;
import com.duod.demoboot.domain.RoleDTO;
import com.duod.demoboot.domain.UserDTO;
import com.duod.demoboot.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 自定义的Realm
 * @Author: duod
 * @Date: 2019/9/9
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 进行权限校验的时候回调用|
     * @param principal
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("授权 doGetAuthorizationInfo");
        String username = (String) principal.getPrimaryPrincipal();
        UserDTO userDTO = userService.findAllUserInfoByUsername(username);

        //角色集合
        List<String> stringRoleList = new ArrayList<>();
        //权限集合
        List<String> stringPermissionList = new ArrayList<>();

        List<RoleDTO> roleList = userDTO.getRoleList();

        for (RoleDTO role : roleList){
            stringRoleList.add(role.getName());
            List<Permission> permissionList = role.getPermissionList();
            for (Permission permission : permissionList){
                if (permission != null){
                    stringPermissionList.add(permission.getName());
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);

        return simpleAuthorizationInfo;
    }
    
    
    /**
     * 用户登录的时候调用
     * @param token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证 doGetAuthenticationInfo");
        //从token获取用户信息，token代表用户输入的
        String username = (String)token.getPrincipal();

        UserDTO userDTO = userService.findAllUserInfoByUsername(username);

        //获取密码
        String password = userDTO.getPassword();
        if (password == null || "".equals(password)){
            return null;
        }
        return new SimpleAuthenticationInfo(username,password,this.getClass().getName());
    }
}
