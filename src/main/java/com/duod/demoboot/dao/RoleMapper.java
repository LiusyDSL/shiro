package com.duod.demoboot.dao;

import com.duod.demoboot.domain.Role;
import com.duod.demoboot.domain.RoleDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleMapper {

    /**
     * @description : 根据用户id查询角色列表
     * @param : userId int
     * @return : List<RoleDTO>
     */
    @Select("select ur.role_id as id,r.name as name,r.description as description from user_role ur" +
            " left join role r on ur.role_id = r.id" +
            " where ur.user_id = #{userId}")
    @Results(
            value = {
                    @Result(id = true,property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "permissionList",column = "id",
                    many = @Many(select = "com.duod.demoboot.dao.PermissionMapper.findPermissionListByRoleId",fetchType = FetchType.DEFAULT))
            }
    )
    List<RoleDTO> findRoleDTOListByUserId(@Param("userId") int userId);

    @Select("select ur.role_id as id,r.name as name,r.description as description from user_role ur" +
            " left join role r on ur.role_id = r.id" +
            " where ur.user_id = #{userId}")
    List<Role> findRoleListByUserId(@Param("userId") int userId);
}
