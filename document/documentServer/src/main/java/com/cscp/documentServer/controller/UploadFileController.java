package com.cscp.documentServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.Constant;
import com.cscp.documentServer.service.IUploadFileService;
import com.cscp.documentServer.service.impl.UploadFileServiceImpl;
import com.cscp.documentServer.support.UploadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 上传文件表 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2020-01-05
 */
@RestController
@RequestMapping("/uploadfile")
public class UploadFileController {
    @Autowired
    IUploadFileService iUploadFileService;

    @PostMapping("/uploadSingleFile")
    public Result uploadSingleFile(MultipartFile file) {
        return ResultUtil.success(iUploadFileService.uploadFile(new UploadEntity("jpg,jpeg,png", Constant.SEPARATOR+"a", file)));
    }

    @PostMapping("/uploadMultiFile")
    public Result uploadMultiFile(List<MultipartFile> files) {
        return ResultUtil.success(iUploadFileService.uploadFiles(new UploadEntity("jpg,jpeg,png", Constant.SEPARATOR+"a", files)));
    }

}
