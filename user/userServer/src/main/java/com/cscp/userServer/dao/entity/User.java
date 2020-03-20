package com.cscp.userServer.dao.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author ckz
 * @since 2019-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> implements Serializable {
    //继承Model将实体类当Mapper使用，同时需要mapper继承BaseMapper
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 学校id
     */
    private String sId;

    /**
     * 专业id
     */
    private String mId;

    /**
     * 年级id
     */
    private String gId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 性别 0-男 1-女
     */
    private Integer sex;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 用户状态0-删除 1-正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 积分
     */
    private Long credits;

}
