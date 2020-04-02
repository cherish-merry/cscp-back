package com.cscp.documentServer.dao.mapper;

import com.cscp.documentCommon.vo.DocumentVo;
import com.cscp.documentServer.dao.entity.ShareDocument;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 共享文档 Mapper 接口
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
public interface ShareDocumentMapper extends BaseMapper<ShareDocument> {
    @Select("<script>select doc.*,d_type.name type,user.name user_name,file.origin_name f_name,file.upload_time " +
            "from share_document doc " +
            "left join share_document_type d_type on doc.t_id=d_type.id " +
            "left join user on user.id=doc.u_id " +
            "left join upload_file file on file.id=doc.f_id " +
            "where doc.status!=0 " +
            "<choose>"+
            "<when test='ids != null and ids.size()!=0'>"+
            "and doc.id in "+
            "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>" +
            "#{id} " +
            "</foreach>" +
            "</when>" +
            "<otherwise> " +
            "and 1!=1 " +
            "</otherwise>" +
            "</choose>"+
            "</script> " )
    List<DocumentVo> getDocumentVoList(@Param("ids") List<String> ids);
}
