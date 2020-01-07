package com.cscp.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cscp.common.utils.*;
import com.cscp.userServer.dao.entity.Grade;
import com.cscp.userServer.dao.entity.Major;
import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.dao.entity.User;
import com.cscp.userServer.dao.mapper.UserMapper;
import com.cscp.userServer.service.IGradeService;
import com.cscp.userServer.service.IMajorService;
import com.cscp.userServer.service.ISchoolService;
import com.cscp.userServer.service.IUserService;
import dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import vo.UserVo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2019-10-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ISchoolService iSchoolService;

    @Autowired
    IGradeService iGradeService;

    @Autowired
    IMajorService iMajorService;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /***
     * @discription 获取当前用户
     * @param: []
     * @return: dto.UserDto
     * @author: ckz
     * @date: 2020/1/2
     */
    @Override
    public UserDto getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            return null;
//        }
//        User user = getOne(new QueryWrapper<User>().eq("username", authentication.getName()));
//        if (user == null) {
//            return null;
//        }
//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(user, userDto);
//        return userDto;
        return null;
    }

    /***
     * @discription 获取用户
     * @param: [gridRequest]
     * @return: com.cscp.common.utils.GridResponse<com.cscp.userServer.dao.entity.User>
     * @author: ckz
     * @date: 2020/1/2
     */
    @Override
    public GridResponseWrapper getGridUsers(GridRequest gridRequest) {
        GridResponseWrapper gridResponseWrapper = new GridResponseWrapper();
        Map<String, Object> filterParams = gridRequest.getFilterParams();
        if (filterParams == null) {
            filterParams = new HashMap<>();
        }
        filterParams.put("status", Constant.TABLE_NORMAL_CODE);
        GridService<User> gridService = new GridService<>();
        gridRequest.setFilterParams(filterParams);
        GridResponse<User> gridResponse = gridService.getGridResponse(userMapper, gridRequest);
        List<School> schools = iSchoolService.list();
        List<Grade> grades = iGradeService.list();
        List<Major> majors = iMajorService.list();
        List<UserVo> userVos = gridResponse.getRecord().stream().map((user) -> {
            UserVo userVo = new UserVo();
            for (School school : schools) {
                if (user.getSId().equals(school.getId())) {
                    userVo.setSchool(school.getName());
                    break;
                }
            }
            for (Grade grade : grades) {
                if (user.getGId().equals(grade.getId())) {
                    userVo.setGrade(grade.getYear());
                }
            }
            for (Major major : majors) {
                if (user.getMId().equals(major.getId())) {
                    userVo.setMajor(major.getName());
                }
            }
            BeanUtils.copyProperties(user, userVo);
            return userVo;
        }).collect(Collectors.toList());
        gridResponseWrapper.setTotal(gridResponse.getTotal());
        gridResponseWrapper.setData(userVos);
        return gridResponseWrapper;
    }

    /***
     * @discription 注册用户
     * @param: [userDto]
     * @return: void
     * @author: ckz
     * @date: 2020/1/6
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void registry(UserDto userDto) {
        User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        if (!CollectionUtils.isEmpty(list(queryWrapper))) {
            throw new ViewException("用户名已存在");
        }
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
    }

    /***
     * @discription 修改用户信息
     * @param: [userDto]
     * @return: void
     * @author: ckz
     * @date: 2020/1/7
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user, "password");
        user.setUpdateTime(LocalDateTime.now());
        this.updateById(user);
    }

    /***
     * @discription 删除用户
     * @param: [ids]
     * @return: void
     * @author: ckz
     * @date: 2020/1/7
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteUser(List<String> ids) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", Constant.TABLE_DELETE_CODE);
        updateWrapper.in("id", ids);
        this.update(updateWrapper);
    }
}
