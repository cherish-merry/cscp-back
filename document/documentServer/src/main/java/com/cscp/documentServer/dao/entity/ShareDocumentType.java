package com.cscp.documentServer.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文档类型
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShareDocumentType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文档类型id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 文档类型名称
     */
    private String name;


}
