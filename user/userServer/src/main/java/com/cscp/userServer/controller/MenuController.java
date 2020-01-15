package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.ViewException;
import com.cscp.userServer.service.IMenuService;
import com.cscp.userServer.service.IUserService;
import dto.UserDto;
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

    @GetMapping("/current")
    public Result getCurrentMenus() {
//        UserDto currentUser = iUserService.getCurrentUser();
//        if (currentUser == null) {
//            throw new ViewException("请先登陆...");
//        }
        return ResultUtil.success(iMenuService.getMenusByUsername("ckz"));
    }

    @GetMapping("/")
    public Result getAllMenus() {
        return ResultUtil.success(iMenuService.getAllMenus());
    }
}
