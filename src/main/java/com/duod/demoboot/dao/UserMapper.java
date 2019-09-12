package com.duod.demoboot.dao;

import com.duod.demoboot.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findByUsername(@Param("username")String username);

    @Select("select * from user where id = #{userId}")
    User findByUserId(@Param("userId") int id);

    @Select("select * from user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
}
