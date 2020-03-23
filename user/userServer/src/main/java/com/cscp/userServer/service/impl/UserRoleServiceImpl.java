package com.cscp.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cscp.common.utils.ViewException;
import com.cscp.userServer.dao.entity.Role;
import com.cscp.userServer.dao.entity.UserRole;
import com.cscp.userServer.dao.mapper.RoleMapper;
import com.cscp.userServer.dao.mapper.UserRoleMapper;
import com.cscp.userServer.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户-角色 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;


    private List<Role> getRolesByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("u_id", userId)
        );
        if(CollectionUtils.isEmpty(userRoles)){
            return null;
        }
        List<String> rIds = userRoles.stream().map(UserRole::getRId).collect(Collectors.toList());
        return roleMapper.selectList(new QueryWrapper<Role>().in("id",rIds));
    }


    @Override
    public List<Role> get(String userId) {
        if(StringUtils.isEmpty(userId)){
            throw new ViewException("user is null");
        }
        LinkedList<Role> roleVos = new LinkedList<>();
        List<Role> roles = getRolesByUserId(userId);
        if(CollectionUtils.isEmpty(roles)){
            return null;
        }
        for (Role role : roles) {
            Role roleVo = new Role();
            BeanUtils.copyProperties(role,roleVo);
            roleVos.add(roleVo);
        }
        return roleVos;
    }

    @Override
    public void delete(String userId, String roleId) {
        if(StringUtils.isEmpty(userId)|StringUtils.isEmpty(roleId)){
            throw new ViewException("user or role is null");
        }
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("u_id",userId).eq("r_id",roleId));
    }

    @Override
    public void post(String userId, String roleId) {
        if(StringUtils.isEmpty(userId)|StringUtils.isEmpty(roleId)){
            throw new ViewException("user or role is null");
        }
        List<Role> roles = getRolesByUserId(userId);
        if(!CollectionUtils.isEmpty(roles)){
            List<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            if(roleIds.contains(roleId)){
                throw new ViewException("user have the role already");
            }
        }
        UserRole userRole = new UserRole().setRId(roleId).setUId(userId);
        save(userRole);
    }
}
