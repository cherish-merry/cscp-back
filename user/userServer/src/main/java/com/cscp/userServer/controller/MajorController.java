package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.dao.entity.Major;
import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.service.IMajorService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 专业 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    IMajorService iMajorService;

    @ApiOperation("获取专业")
    @PostMapping("/get")
    public Result get(@RequestBody GridRequest gridRequest) {
        return ResultUtil.success(iMajorService.get((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @ApiOperation("添加专业")
    @PostMapping("/")
    public Result post(@RequestBody Major major) {
        iMajorService.post(major);
        return ResultUtil.success();
    }

    @ApiOperation("更新专业")
    @PutMapping("/")
    public Result put(@RequestBody Major major) {
        iMajorService.put(major);
        return ResultUtil.success();
    }


    @ApiOperation("删除专业")
    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> ids) {
        iMajorService.delete(ids);
        return ResultUtil.success();
    }
}
