package com.cscp.userServer.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cscp.userClient.api.UserApi;
import com.cscp.userServer.dao.entity.User;
import com.cscp.userServer.service.IUserService;
import dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/getCurrentUser")
    public UserDto getCurrentUser() {
        return iUserService.getCurrentUser();
    }
}
