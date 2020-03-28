package com.cscp.userServer.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ckz
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户类型id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户类型
     */
    private String name;


}
