package com.duod.demoboot.domain;


import java.util.List;

public class RoleDTO {

  private int id;
  private String name;
  private String description;
  private List<Permission> permissionList;

  public List<Permission> getPermissionList() {
    return permissionList;
  }

  public void setPermissionList(List<Permission> permissionList) {
    this.permissionList = permissionList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "RoleDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", permissionList=" + permissionList +
            '}';
  }
}
