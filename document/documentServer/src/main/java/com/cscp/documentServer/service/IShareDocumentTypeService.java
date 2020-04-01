package com.cscp.documentServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.documentServer.dao.entity.ShareDocumentType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文档类型 服务类
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
public interface IShareDocumentTypeService extends IService<ShareDocumentType> {

    GridResponse<ShareDocumentType> get(GridRequest gridRequest);
}
