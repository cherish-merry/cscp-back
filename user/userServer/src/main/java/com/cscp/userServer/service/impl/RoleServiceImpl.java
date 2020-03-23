package com.cscp.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cscp.common.utils.*;
import com.cscp.userServer.dao.entity.Role;
import com.cscp.userServer.dao.entity.UserRole;
import com.cscp.userServer.dao.mapper.RoleMapper;
import com.cscp.userServer.service.IRoleService;
import com.cscp.userServer.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    IUserRoleService iUserRoleService;



    /***
     * @discription grid 获取角色
     * @param: [gridRequest]
     * @return: com.cscp.common.utils.GridResponseWrapper
     * @author: ckz
     * @date: 2020/1/6
     */
    @Override
    public GridResponse<Role> getGridRoles(GridRequest gridRequest) {
        Map<String, Object> filterParams = gridRequest.getFilterParams();
        if (filterParams == null) {
            filterParams = new HashMap<>();
        }
        gridRequest.setFilterParams(filterParams);
        filterParams.put("status", Constant.TABLE_NORMAL_CODE);
        return new GridService<Role>().getGridResponse(roleMapper, gridRequest);
    }

    /***
     * @discription 删除角色
     * @param: [roleIds]
     * @return: void
     * @author: ckz
     * @date: 2020/1/7
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteRole(List<String> roleIds) {
        UpdateWrapper<Role> roleUpdateWrapper = new UpdateWrapper<>();
        roleUpdateWrapper.set("status", Constant.TABLE_DELETE_CODE);
        roleUpdateWrapper.in("id", roleIds);
        this.update(roleUpdateWrapper);
    }

    /***
     * @discription 添加角色
     * @param: [role]
     * @return: void
     * @author: ckz
     * @date: 2020/1/7
     */
    @Override
    public void addRole(Role role) {
        if (!CollectionUtils.isEmpty(this.list(new QueryWrapper<Role>().eq("role_name", role.getRoleName())))) {
            throw new ViewException("角色已存在");
        }
        this.save(role);
    }

    /***
     * @discription 根据用户id 获取角色
     * @param: [userId]
     * @return: java.util.List<com.cscp.userServer.dao.entity.Role>
     * @author: ckz
     * @date: 2020/1/7
     */
    @Override
    public GridResponse<Role> getRolesByUserId(String userId) {
        GridResponse<Role> gridResponse = new GridResponse<>();
        List<String> roleIds = iUserRoleService.list(new QueryWrapper<UserRole>().eq("u_id", userId))
                .stream().map(UserRole::getRId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        List<Role> result = this.list(new QueryWrapper<Role>().eq("status", Constant.TABLE_NORMAL_CODE).in("id", roleIds));
        gridResponse.setRecords(result);
        gridResponse.setTotal(result.size());
        return gridResponse;
    }
}
