package com.cscp.documentServer.service.impl;

import com.cscp.documentServer.dao.entity.Task;
import com.cscp.documentServer.dao.mapper.TaskMapper;
import com.cscp.documentServer.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-02-08
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
