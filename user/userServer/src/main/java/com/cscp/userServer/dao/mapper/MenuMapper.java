package com.cscp.userServer.dao.mapper;

import com.cscp.userServer.dao.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT * FROM menu WHERE menu.id IN (SELECT role_menu.m_id FROM role_menu WHERE role_menu.r_id IN (SELECT role.id FROM\n" +
            "\trole WHERE role.id IN (SELECT user_role.r_id FROM user_role WHERE user_role.u_id = (SELECT user.id FROM user WHERE\n" +
            "user.username = #{username} AND user.`status` = 1)) AND role.`status` = 1)) AND menu.`status` = 1")
    List<Menu> getMenuByUserName(String username);
}
