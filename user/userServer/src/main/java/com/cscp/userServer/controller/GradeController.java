package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.dao.entity.Grade;
import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.service.IGradeService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 年级 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    IGradeService iGradeService;

    @ApiOperation("获取年级")
    @PostMapping("/get")
    public Result get(@RequestBody GridRequest gridRequest) {
        return ResultUtil.success(iGradeService.get((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @ApiOperation("添加年级")
    @PostMapping("/")
    public Result post(@RequestBody Grade grade) {
        iGradeService.post(grade);
        return ResultUtil.success();
    }

    @ApiOperation("更新年级")
    @PutMapping("/")
    public Result put(@RequestBody Grade grade) {
        iGradeService.put(grade);
        return ResultUtil.success();
    }


    @ApiOperation("删除年级")
    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> ids) {
        iGradeService.delete(ids);
        return ResultUtil.success();
    }
}
