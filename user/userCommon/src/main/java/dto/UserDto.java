package dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/23 - 11:09
 */
@Data
public class  UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 积分
     */
    private Long credits;
}
