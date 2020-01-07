package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.userServer.service.IUserRoleService;
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

    @PostMapping("/bindUserRole")
    public Result bindUserRole(String userId,@RequestParam(required = false) List<String> roleIds){
        iUserRoleService.bindUserRole(userId,roleIds);
        return ResultUtil.success();
    }
}
