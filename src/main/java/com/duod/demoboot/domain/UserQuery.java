package com.duod.demoboot.domain;

/**
 * @Description: 用于登录时接受参数
 * @Author: duod
 * @Date: 2019/9/12
 */
public class UserQuery {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
