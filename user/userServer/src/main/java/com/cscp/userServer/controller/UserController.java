package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.service.IUserService;
import dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/getGridUsers")
    public Result getGridUsers(@RequestBody(required = false) GridRequest gridRequest) {
        return ResultUtil.success(iUserService.getGridUsers(gridRequest));
    }

    @PostMapping("/registry")
    public Result registry(@RequestBody UserDto userDto) {
        iUserService.registry(userDto);
        return ResultUtil.success();
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody UserDto userDto) {
        iUserService.updateUser(userDto);
        return ResultUtil.success();
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestParam List<String> ids) {
        iUserService.deleteUser(ids);
        return ResultUtil.success();
    }
}
