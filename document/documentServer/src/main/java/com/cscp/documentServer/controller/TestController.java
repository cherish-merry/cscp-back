package com.cscp.documentServer.controller;

import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.documentServer.client.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/22 - 21:39
 */
@Api(description = "document模块相关api")
@RestController
public class TestController {
    @Autowired
    UserClient userClient;
    
    @ApiOperation(value = "获取user信息",notes = "document-->>>>user", response = Result.class)
        @GetMapping("/getUserByUsername")
        public Result getUserByUsername(@ApiParam(value = "用户名") @RequestParam String username) {
        return ResultUtil.success(userClient.getUserByUsername(username));
    }
}
