package com.cscp.documentServer.dao.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 上传文件表
 * </p>
 *
 * @author ckz
 * @since 2020-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 上传路径
     */
    private String location;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 上传文件名
     */
    private String originName;

    /**
     * 文件大小
     */
    private Integer size;


}
