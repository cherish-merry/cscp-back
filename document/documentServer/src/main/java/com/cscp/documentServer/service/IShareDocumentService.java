package com.cscp.documentServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.documentCommon.vo.DocumentVo;
import com.cscp.documentServer.dao.entity.ShareDocument;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 共享文档 服务类
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
public interface IShareDocumentService extends IService<ShareDocument> {
    GridResponse<?> getDocumentVoList(GridRequest gridRequest);
}
