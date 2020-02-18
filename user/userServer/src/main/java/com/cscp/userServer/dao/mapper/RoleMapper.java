package com.cscp.userServer.dao.mapper;

import com.cscp.userServer.dao.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT role.* from role\n" +
            "JOIN `user` on `user`.`status`=1 and `user`.username=#{username}\n" +
            "JOIN user_role on user_role.r_id=role.id and user_role.u_id=`user`.id\n" +
            "where role.`status`=1")
    List<Role> getRolesByUsername(String username);
}
