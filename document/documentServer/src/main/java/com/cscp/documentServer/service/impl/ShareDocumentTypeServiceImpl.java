package com.cscp.documentServer.service.impl;

import com.cscp.documentServer.dao.entity.ShareDocumentType;
import com.cscp.documentServer.dao.mapper.ShareDocumentTypeMapper;
import com.cscp.documentServer.service.IShareDocumentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
