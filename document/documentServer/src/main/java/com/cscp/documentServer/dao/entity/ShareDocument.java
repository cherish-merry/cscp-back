package com.cscp.documentServer.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 共享文档
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShareDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文档id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    private String uId;

    /**
     * 文档类型id
     */
    private String tId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 下载量
     */
    private Long documentCount;

    /**
     * 下载所需积分
     */
    private Long credits;

    /**
     * 上传文档id
     */
    private String fId;


}
