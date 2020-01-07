package com.cscp.documentServer.service.impl;

import com.cscp.documentServer.dao.entity.ShareDocument;
import com.cscp.documentServer.dao.mapper.ShareDocumentMapper;
import com.cscp.documentServer.service.IShareDocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 共享文档 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
@Service
public class ShareDocumentServiceImpl extends ServiceImpl<ShareDocumentMapper, ShareDocument> implements IShareDocumentService {

}
