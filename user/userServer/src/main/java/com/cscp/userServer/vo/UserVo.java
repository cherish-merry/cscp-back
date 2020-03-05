package com.cscp.userServer.vo;

import com.cscp.userServer.dao.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
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
    private String school;

    /**
     * 专业id
     */
    private String major;

    /**
     * 年级id
     */
    private Integer grade;

    /**
     * 学生类型 0-本科生 1-研究生
     */
    private Integer type;

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

    /*
    * 角色
    * */
    private List<String> roles;
}
