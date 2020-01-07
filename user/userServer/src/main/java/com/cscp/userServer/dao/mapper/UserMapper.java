package com.cscp.userServer.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cscp.userServer.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import vo.UserVo;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author ckz
 * @since 2019-10-26
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT `user`.*,school.`name` as school,major.`name` as major,grade.`year` as grade\n" +
            "FROM `user`,school,grade,major where `user`.`status`=1 and `user`.s_id=school.id \n" +
            "and `user`.g_id=grade.id and `user`.m_id=major.id  ")
    IPage<User> selectVo(Page<User> page);
}
