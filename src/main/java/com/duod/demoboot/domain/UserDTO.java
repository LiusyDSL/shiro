package com.duod.demoboot.domain;


import java.util.Date;
import java.util.List;


public class UserDTO {

  private int id;
  private String username;
  private String password;
  private Date createTime;
  private String salt;
  private List<RoleDTO> roleList;

  public List<RoleDTO> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<RoleDTO> roleList) {
    this.roleList = roleList;
  }

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


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


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", createTime=" + createTime +
            ", salt='" + salt + '\'' +
            ", roleList=" + roleList +
            '}';
  }
}
