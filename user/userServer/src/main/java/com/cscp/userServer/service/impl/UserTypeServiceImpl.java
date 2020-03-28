package com.cscp.userServer.service.impl;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.common.utils.GridService;
import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.dao.entity.UserType;
import com.cscp.userServer.dao.mapper.UserTypeMapper;
import com.cscp.userServer.service.IUserTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-03-28
 */
@Service
public class UserTypeServiceImpl extends ServiceImpl<UserTypeMapper, UserType> implements IUserTypeService {
    @Autowired
    UserTypeMapper userTypeMapper;

    @Override
    public GridResponse<UserType> get(GridRequest gridRequest) {
        return new GridService<UserType>().getGridResponse(userTypeMapper, gridRequest);
    }

    @Override
    public void post(UserType userType) {
        save(userType);
    }

    @Override
    public void put(UserType userType) {
        updateById(userType);
    }

    @Override
    public void delete(List<String> ids) {
        removeByIds(ids);
    }
}
