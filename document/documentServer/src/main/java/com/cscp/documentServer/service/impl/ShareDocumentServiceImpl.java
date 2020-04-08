package com.cscp.documentServer.service.impl;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.common.utils.GridService;
import com.cscp.documentCommon.vo.DocumentVo;
import com.cscp.documentServer.dao.entity.ShareDocument;
import com.cscp.documentServer.dao.mapper.ShareDocumentMapper;
import com.cscp.documentServer.service.IShareDocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private ShareDocumentMapper documentMapper;

    GridService<ShareDocument> documentGridService=new GridService<>();

    @Override
    public GridResponse<?> getDocumentVoList(GridRequest gridRequest) {
        Map<String, Object> filterParams = gridRequest.getFilterParams();
        if (filterParams==null){
            filterParams=new HashMap<>();
        }
        GridResponse<ShareDocument> gridResponse = documentGridService.getGridResponse(documentMapper, gridRequest);
        List<DocumentVo> documentVoList = documentMapper.getDocumentVoList(gridResponse.getRecords().stream().map(e -> e.getId()).collect(Collectors.toList()));
        GridResponse<DocumentVo> response=new GridResponse();
        response.setRecords(documentVoList);
        response.setTotal(gridResponse.getTotal());
        return response;
    }
}
