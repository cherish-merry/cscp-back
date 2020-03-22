package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.userServer.service.IUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户-角色 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@RestController
@RequestMapping("/user-role")
public class UserRoleController {
    @Autowired
    IUserRoleService iUserRoleService;

    @ApiOperation("获取用户角色")
    @GetMapping("/{userId}")
    public Result get(@PathVariable String userId){
        return ResultUtil.success(iUserRoleService.get(userId));
    }


    @ApiOperation("删除用户角色")
    @DeleteMapping("/")
    public Result delete(@RequestParam String userId,@RequestParam String roleId){
        iUserRoleService.delete(userId,roleId);
        return ResultUtil.success();
    }

    @ApiOperation("添加用户角色")
    @PostMapping("/")
    public Result post(@RequestParam String userId,@RequestParam String roleId){
        iUserRoleService.post(userId,roleId);
        return ResultUtil.success();
    }
}
