package com.cscp.documentServer.service;

import com.cscp.documentServer.dao.entity.UploadFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cscp.documentServer.support.UploadEntity;

import java.util.List;

/**
 * <p>
 * 上传文件表 服务类
 * </p>
 *
 * @author ckz
 * @since 2020-01-05
 */
public interface IUploadFileService extends IService<UploadFile> {
    String uploadFile(UploadEntity uploadEntity);

    List<String> uploadFiles(UploadEntity uploadEntity);
}
