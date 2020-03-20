package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.userServer.service.IMenuService;
import com.cscp.userServer.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IMenuService iMenuService;

    @ApiOperation("获取当前用户的菜单")
    @GetMapping("/current")
    public Result current() {
//        UserDto currentUser = iUserService.current();
//        if (currentUser == null) {
//            throw new ViewException("请先登陆...");
//        }
        return ResultUtil.success(iMenuService.getMenusByUsername("ckz"));
    }

    @ApiOperation("获取所有菜单")
    @GetMapping("/")
    public Result get() {
        return ResultUtil.success(iMenuService.getAllMenus());
    }
}
