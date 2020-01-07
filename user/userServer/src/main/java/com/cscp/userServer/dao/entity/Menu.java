package com.cscp.userServer.dao.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 状态
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Menu implements Serializable,Comparable<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 父级菜单id
     */
    private String parent;

    /**
     * 菜单排序
     */
    private Integer orderNum;

    private Integer status;

    @TableField(exist = false)
    private List<Menu> children=new LinkedList<>();

    @Override
    public int compareTo(Menu o) {
        return o.orderNum-orderNum;
    }
}
