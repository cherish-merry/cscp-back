package com.cscp.userServer.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cscp.common.utils.GridRequest;
import com.cscp.userClient.api.UserApi;
import com.cscp.userServer.dao.entity.Role;
import com.cscp.userServer.dao.entity.User;
import com.cscp.userServer.dao.mapper.RoleMapper;
import com.cscp.userServer.service.IRoleService;
import com.cscp.userServer.service.IUserService;
import dto.RoleDto;
import dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/23 - 11:29
 */
@RestController
@RequestMapping("/api")
public class UserApiImpl implements UserApi {
    @Autowired
    IUserService iUserService;

    @Autowired
    RoleMapper roleMapper;

    @PostMapping("/getUserByUsername")
    @Override
    public UserDto getUserByUsername(@RequestBody String username) {
        User user = iUserService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public UserDto getUserById(String id) {
        User user = iUserService.getById(id);
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    @PostMapping("/getCurrentUser")
    public UserDto getCurrentUser() {
        return iUserService.current();
    }

    @GetMapping("/{username}/roles")
    @Override
    public List<RoleDto> getRolesByUsername(@PathVariable("username") String username) {
        List<Role> roles = roleMapper.getRolesByUsername(username);
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }
        return roles.stream().map(role -> {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            return roleDto;
        }).collect(Collectors.toList());
    }
}
