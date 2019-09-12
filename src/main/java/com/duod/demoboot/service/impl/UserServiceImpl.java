package com.duod.demoboot.service.impl;

import com.duod.demoboot.dao.PermissionMapper;
import com.duod.demoboot.dao.RoleMapper;
import com.duod.demoboot.dao.UserMapper;
import com.duod.demoboot.domain.*;
import com.duod.demoboot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper  userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDTO findAllUserInfoByUsername(String username) {
        User user = userMapper.findByUsername(username);
        List<RoleDTO> roleDTOList = roleMapper.findRoleDTOListByUserId(user.getId());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setRoleList(roleDTOList);
        return userDTO;
    }

    @Override
    public User findSimpleUserInfoById(int userId) {
        return null;
    }

    @Override
    public User findSimpleUserInfoByUsername(String username) {
        return null;
    }
}
