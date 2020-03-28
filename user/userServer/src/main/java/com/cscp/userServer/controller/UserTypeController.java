package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.dao.entity.UserType;
import com.cscp.userServer.service.IUserTypeService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ckz
 * @since 2020-03-28
 */
@RestController
@RequestMapping("/user-type")
public class UserTypeController {
    @Autowired
    IUserTypeService iUserTypeService;

    @ApiOperation("获取用户类型")
    @PostMapping("/get")
    public Result get(@RequestBody(required = false) GridRequest gridRequest) {
        return ResultUtil.success(iUserTypeService.get((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @ApiOperation("添加用户类型")
    @PostMapping("/")
    public Result post(@RequestBody UserType userType) {
        iUserTypeService.post(userType);
        return ResultUtil.success();
    }

    @ApiOperation("更新用户类型")
    @PutMapping("/")
    public Result put(@RequestBody UserType userType) {
        iUserTypeService.put(userType);
        return ResultUtil.success();
    }


    @ApiOperation("删除用户类型")
    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> ids) {
        iUserTypeService.delete(ids);
        return ResultUtil.success();
    }
}
