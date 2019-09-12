package com.duod.demoboot.dao;

import com.duod.demoboot.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

    /**
     * @Description: 根据角色id查询对应权限列表
     * @param : roleId
     * @return : List<Permission>
     */
    @Select("select p.id as id,p.name as name,p.url as url from permission p " +
            "    left join role_permission rp on p.id = rp.permission_id where rp.role_id = #{roleId}")
    List<Permission> findPermissionListByRoleId(@Param("roleId") int id);

}
