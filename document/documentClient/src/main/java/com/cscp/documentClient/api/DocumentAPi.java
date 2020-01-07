package com.cscp.documentClient.api;

import com.cscp.documentCommon.dto.DocumentDto;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/22 - 21:46
 */
public interface DocumentAPi {
    public List<DocumentDto> getAllDocuments();
}
