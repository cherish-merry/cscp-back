package com.cscp.documentServer.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cscp.documentServer.dao.entity.LessonClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ckz
 * @since 2020-02-08
 */
public interface LessonClassMapper extends BaseMapper<LessonClass> {

    @Select("select lesson_class.* from user_class JOIN lesson_class " +
            "on user_class.c_id=lesson_class.id " +
            "where user_class.u_id=#{userId}")
    public IPage<LessonClass> getLessonClassByUser(IPage page, @Param("userId") String userId);

}
