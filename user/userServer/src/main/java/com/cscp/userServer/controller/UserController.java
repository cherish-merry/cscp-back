package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.service.IRoleService;
import com.cscp.userServer.service.IUserService;
import dto.UserDto;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2019-10-26
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService iUserService;

    @Autowired
    IRoleService iRoleService;


    @GetMapping("/me")
    public Authentication me(Authentication authentication) {
        return authentication;
    }

    @ApiOperation("获取当前用户")
    @GetMapping("/current")
    public Result current() {
        return ResultUtil.success(iUserService.current());
    }

    @ApiOperation("获取用户")
    @GetMapping("/")
    public Result get(@RequestParam(required = false) GridRequest gridRequest) {
        return ResultUtil.success(iUserService.get((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @ApiOperation("添加用户")
    @PostMapping("/")
    public Result post(@RequestBody UserDto userDto) {
        iUserService.post(userDto);
        return ResultUtil.success();
    }

    @ApiOperation("更新用户")
    @PutMapping("/")
    public Result put(@RequestBody UserDto userDto) {
        iUserService.put(userDto);
        return ResultUtil.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> ids) {
        iUserService.delete(ids);
        return ResultUtil.success();
    }

    @ApiOperation("通过用户id获取角色")
    @GetMapping("/{userId}/roles")
    public Result roles(@PathVariable("userId") String userId) {
        return ResultUtil.success(iRoleService.getRolesByUserId(userId));
    }
}
