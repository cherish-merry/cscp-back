package com.cscp.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cscp.userServer.dao.entity.UserRole;
import com.cscp.userServer.dao.mapper.UserRoleMapper;
import com.cscp.userServer.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    /***
     * @discription 绑定用户角色
     * @param: [userId, roleIds]
     * @return: void
     * @author: ckz
     * @date: 2020/1/6
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void bindUserRole(String userId, List<String> roleIds) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id", userId);
        List<UserRole> ownerRoles = list(queryWrapper);
        List<String> ownerRolesIds = ownerRoles.stream().map(UserRole::getRId).collect(Collectors.toList());
        List<String> toDelete;
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UserRole> toInsert = roleIds.stream().filter(s -> !ownerRolesIds.contains(s)).map((s) -> {
                UserRole userRole = new UserRole();
                userRole.setUId(userId);
                userRole.setRId(s);
                return userRole;
            }).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(toInsert)) {
                saveBatch(toInsert);
            }
            toDelete = ownerRolesIds.stream().filter(s -> !roleIds.contains(s)).collect(Collectors.toList());
        } else {
            toDelete = ownerRolesIds;
        }
        if (!CollectionUtils.isEmpty(toDelete)) {
            QueryWrapper<UserRole> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("u_id", userId);
            queryWrapper.in("r_id", toDelete);
            remove(queryWrapper1);
        }
    }
}
