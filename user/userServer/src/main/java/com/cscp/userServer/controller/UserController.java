package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.service.IUserService;
import dto.UserDto;
import org.apache.commons.lang.ObjectUtils;
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
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService iUserService;

    @GetMapping("/")
    public Result get(@RequestParam(required = false) GridRequest gridRequest) {
        return ResultUtil.success(iUserService.get((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @PostMapping("/")
    public Result post(@RequestBody UserDto userDto) {
        iUserService.post(userDto);
        return ResultUtil.success();
    }

    @PutMapping("/")
    public Result put(@RequestBody UserDto userDto) {
        iUserService.put(userDto);
        return ResultUtil.success();
    }

    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> ids) {
        iUserService.delete(ids);
        return ResultUtil.success();
    }
}
