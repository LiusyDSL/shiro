package com.duod.demoboot.service;

import com.duod.demoboot.domain.User;
import com.duod.demoboot.domain.UserDTO;

public interface UserService {
    
    /**
     * 获取用户全部信息，包括角色，权限
     * @param username
     * @return
     */
    UserDTO findAllUserInfoByUsername(String username);

    /**
     * 获取用户基本信息
     * @param userId
     * @return 
     */
    User findSimpleUserInfoById(int userId);
    
    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    User findSimpleUserInfoByUsername(String username);
}
