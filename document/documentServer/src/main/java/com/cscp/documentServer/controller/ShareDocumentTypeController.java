package com.cscp.documentServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.documentServer.dao.entity.ShareDocumentType;
import com.cscp.documentServer.service.IShareDocumentTypeService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文档类型 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/shareDocumentType")
public class ShareDocumentTypeController {

    @Autowired
    IShareDocumentTypeService documentTypeService;

    @ApiOperation("获取文件类型(不传id)")
    @PostMapping("/get")
    public Result get(@RequestBody(required = false) GridRequest gridRequest) {
        return ResultUtil.success(documentTypeService.get((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @ApiOperation("添加文件类型")
    @PostMapping("/")
    public Result post(@RequestBody ShareDocumentType type) {
        documentTypeService.save(type);
        return ResultUtil.success();
    }

    @ApiOperation("更新文件类型")
    @PutMapping("/")
    public Result put(@RequestBody ShareDocumentType type) {
        documentTypeService.updateById(type);
        return ResultUtil.success();
    }


    @ApiOperation("删除文件类型")
    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> ids) {
        documentTypeService.removeByIds(ids);
        return ResultUtil.success();
    }

}
