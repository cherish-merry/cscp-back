package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.service.ISchoolService;
import dto.UserDto;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学校 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    ISchoolService iSchoolService;

    @ApiOperation("获取学校")
    @PostMapping("/get")
    public Result get(@RequestBody(required = false) GridRequest gridRequest) {
        return ResultUtil.success(iSchoolService.get((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @ApiOperation("添加学校")
    @PostMapping("/")
    public Result post(@RequestBody School school) {
        iSchoolService.post(school);
        return ResultUtil.success();
    }

    @ApiOperation("更新学校")
    @PutMapping("/")
    public Result put(@RequestBody School school) {
        iSchoolService.put(school);
        return ResultUtil.success();
    }


    @ApiOperation("删除学校")
    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> ids) {
        iSchoolService.delete(ids);
        return ResultUtil.success();
    }
}
