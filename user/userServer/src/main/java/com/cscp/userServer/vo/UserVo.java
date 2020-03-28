package com.cscp.userServer.vo;

import com.cscp.userServer.dao.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/1/2 - 15:34
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 学校id
     */
    private String sId;

    private String school;

    /**
     * 专业id
     */
    private String mId;

    private String major;

    /**
     * 年级id
     */
    private String gId;

    private Integer grade;


    /**
     * 用户类型id
     */
    private String tId;

    private String type;


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
     * 电子邮箱
     */
    private String email;

    /**
     * 积分
     */
    private Long credits;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /*
    * 角色
    * */
    private List<String> roles;
}
