package com.cscp.documentServer.service.impl;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.common.utils.GridService;
import com.cscp.documentServer.dao.entity.ShareDocumentType;
import com.cscp.documentServer.dao.mapper.ShareDocumentTypeMapper;
import com.cscp.documentServer.service.IShareDocumentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文档类型 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
@Service
public class ShareDocumentTypeServiceImpl extends ServiceImpl<ShareDocumentTypeMapper, ShareDocumentType> implements IShareDocumentTypeService {

    @Resource
    ShareDocumentTypeMapper shareDocumentTypeMapper;

    @Override
    public GridResponse<ShareDocumentType> get(GridRequest gridRequest) {
        return  new GridService<ShareDocumentType>().getGridResponse(shareDocumentTypeMapper, gridRequest);
    }
}
