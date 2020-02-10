package com.cscp.documentServer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.*;
import com.cscp.documentCommon.dto.LessonClassDto;
import com.cscp.documentCommon.utils.DocumentUtil;
import com.cscp.documentServer.client.UserClient;
import com.cscp.documentServer.dao.entity.*;
import com.cscp.documentServer.dao.mapper.LessonClassMapper;
import com.cscp.documentServer.dao.mapper.TaskMapper;
import com.cscp.documentServer.service.*;
import com.cscp.documentServer.service.impl.UploadFileServiceImpl;
import com.cscp.documentServer.support.UploadEntity;
import dto.UserDto;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.cscp.common.utils.Constant.*;

/**
 * Created by 11727 on 2020/2/8 15:30
 */
@RestController
@RequestMapping("/homework")
public class HomeworkController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ILessonClassService iLessonClassService;
    
    @Resource
    UserClient userClient;

    @Resource
    IUploadFileService uploadFileService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IHomeworkService homeworkService;

    @Resource
    private LessonClassMapper lessonClassMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private IUserClassService userClassService;


    @GetMapping("/getCurrent")
    public Result getCurrent(){
        return ResultUtil.success(userClient.getCurrentUser());
    }

    //    获取学生加入的课程班级
    @ApiOperation("获取学生加入的课程班级")
    @RequestMapping("/getMyLessonClasses")
    public Result getMyLessonClasses(com.cscp.common.utils.Page page){
        UserDto currentUser = userClient.getCurrentUser();
        return ResultUtil.success(iLessonClassService.getLessonClassByUser(Page.getIPage(page),currentUser.getId()));
    }

    //    获取管理者管理的课程班级
    @GetMapping("/getManageLessonClasses")
    public Result getManageClasses(com.cscp.common.utils.Page page){
        UserDto currentUser = userClient.getCurrentUser();
        IPage<LessonClass> lessonClassIPage = iLessonClassService.page(Page.getIPage(page), new QueryWrapper<LessonClass>().eq("founder_id", currentUser.getId()));
        GridResponse gridResponse = GridResponse.getResponseByPage(lessonClassIPage);
        return ResultUtil.success(gridResponse);
    }

    //    获取所有课程班级
    @ApiOperation("获取所有课程班级")
    @GetMapping("/getAllClasses")
    public Result getAllClasses(GridRequest gridRequest){
        GridService<LessonClass> gridService=new GridService<>();
        GridResponse<LessonClass> gridResponse = gridService.getGridResponse(lessonClassMapper, gridRequest);
        return ResultUtil.success(gridResponse);
    }

    @ApiOperation("根据课程码得到班级课程")
    @GetMapping("/getLessonClassByCode")
    public Result getLessonClassByCode(Page page,String id){
        QueryWrapper<LessonClass> queryWrapper=new QueryWrapper<LessonClass>().eq("id", id);
        IPage iPage = iLessonClassService.page(Page.getIPage(page),queryWrapper);
        return ResultUtil.success(GridResponse.getResponseByPage(iPage));
    }

    //    学生加入课程班级
    @ApiOperation("学生加入课程班级")
    @GetMapping("/joinClass")
    public Result joinClass(String cid){
        UserDto currentUser = userClient.getCurrentUser();
        UserClass userClass=new UserClass();
        userClass.setCId(cid);
        userClass.setUId(currentUser.getId());
        userClassService.save(userClass);
        log.info(currentUser.getName()+"加入该课程，课程id："+cid);
        return ResultUtil.success();
    }

    @ApiOperation("创建新课程班级")
    @PostMapping("/createClass")
    public Result createClass(@RequestBody LessonClassDto newClassDto){
        LessonClass newclass=new LessonClass();
        BeanUtils.copyProperties(newClassDto,newclass);
        newclass.setId(UUID.randomUUID().toString());
        UserDto currentUser = userClient.getCurrentUser();
        newclass.setFounderId(currentUser.getId());
        newclass.setFounderName(currentUser.getName());
        iLessonClassService.save(newclass);
        log.info("===>>>"+currentUser.getUsername()+"创建课程："+newclass.getName());
        return ResultUtil.success();
    }

    @ApiOperation("获取需要提交该任务的总人数")
    @GetMapping("/getClassMemCount")
    public Result getClassMemCount(String tId){
        Task task=taskService.getOne(new QueryWrapper<Task>().eq("id", tId));
        String cId=task.getCId();
        int count=userClassService.count(new QueryWrapper<UserClass>().eq("c_id",cId));
        return ResultUtil.success(count);
    }


    //    检测学生是否已经提交该作业
    @ApiOperation("检测当前登录学生是否已经提交该作业")
    @GetMapping("/submited")
    public Result submited(String tId){
        UserDto currentUser = userClient.getCurrentUser();
        boolean submited;
        int count = homeworkService.count(new QueryWrapper<Homework>().eq("t_id", tId).eq("author",currentUser.getUsername()));
        if (count!=0)
            submited= true;
        else
            submited= false;
        return ResultUtil.success(submited);
    }

    @ApiOperation("获取任务已提交人数")
    @GetMapping("/getSubmitedCount")
    public Result getSubmitedCount(String tId){
        int count = homeworkService.count(new QueryWrapper<Homework>().eq("id", tId));
        return ResultUtil.success(count);
    }

    //    获取该课程的作业任务
    @ApiOperation("获取该课程的作业任务")
    @GetMapping("/getTasks")
    public Result getTasks(GridRequest gridRequest,String c_id){
        GridService<Task> gridService=new GridService<>();
        Map<String, Object> filterParams = gridRequest.getFilterParams();
        if (filterParams==null)
            filterParams=new HashMap<>();
        filterParams.put("c_id",c_id);
        GridResponse<Task> gridResponse = gridService.getGridResponse(taskMapper, gridRequest);
        return  ResultUtil.success(gridResponse);
    }

    //    创建新任务
    @ApiOperation("创建新任务")
    @PostMapping("/createTask")
    public Result createTask(@RequestBody Task task){
        task.setId(UUID.randomUUID().toString());
        taskService.save(task);
        return  ResultUtil.success();
    }

    @ApiOperation("删除任务")
    @GetMapping("/deleteTask")
    public Result deleteTask(String t_id){
        taskService.removeById(t_id);
        return ResultUtil.success();
    }


    //    上传作业文件
    @Transactional
    @ApiOperation("删除任务")
    @PostMapping("/upload")
    public Result upload(MultipartFile file, String tId) throws IOException {
        UserDto currentUser = userClient.getCurrentUser();
        Homework preHomework = homeworkService.getOne(new QueryWrapper<Homework>().eq("id", tId).eq("author",currentUser.getUsername()));
        if (preHomework!=null){
            UploadFile uploadFile = uploadFileService.getById(preHomework.getFId());
            DocumentUtil.deleteFile(uploadFile.getLocation());
            homeworkService.removeById(preHomework.getId());
            log.info("用户:" +currentUser.getUsername() + "==覆盖了上传作业文件：" + preHomework.getName());
        }
        Task task=taskService.getOne(new QueryWrapper<Task>().eq("t_id", tId));
        LessonClass myclass=iLessonClassService.getOne(new QueryWrapper<LessonClass>().eq("id", task.getCId()));
        String fileName=file.getOriginalFilename();
        //判断是否为IE浏览器的文件名，IE浏览器下文件名会带有盘符信息
        // Cut off at latest possible point
        int pos = fileName.lastIndexOf(SEPARATOR);
        if (pos != -1)  {
            // Any sort of path separator found...
            fileName = fileName.substring(pos + 1);
        }
        Homework homework = new Homework();
        homework.setId(UUID.randomUUID().toString());
        homework.setAuthor(currentUser.getUsername());
        homework.setTId(tId);
        homework.setName(fileName);
        String fId=uploadFileService.uploadFile(new UploadEntity(null, SEPARATOR + HOMEWORK_DIR_NAME +SEPARATOR+myclass.getName()+task.getName(), file));
        homework.setFId(fId);
        homeworkService.save(homework);
        log.info("用户:" + currentUser.getUsername() + "==>上传作业文件：" + fileName);
        return ResultUtil.success();
    }

    @GetMapping("/hdownload/{t_id}")
    @Transactional
    public void download(HttpServletResponse response, @PathVariable String t_id) throws IOException {
        Object fileLock = new Object();
        Task task=taskService.getOne(new QueryWrapper<Task>().eq("t_id", t_id));
        LessonClass myclass=iLessonClassService.getOne(new QueryWrapper<LessonClass>().eq("id", task.getCId()));
        //获取服务器文件
        List<Homework> homeworkList = homeworkService.list(new QueryWrapper<Homework>().eq("t_id", t_id));
        String documentPath=DOCUMENT_DIR_PATH +SEPARATOR + HOMEWORK_DIR_NAME +SEPARATOR+myclass.getName()+task.getName();
        String zipPath=DOCUMENT_DIR_PATH+SEPARATOR+ Constant.HOMEWORKZIP_DIR_NAME;
        String fileName=myclass.getName()+"_"+task.getName()+".zip";
        String destPath=zipPath+SEPARATOR+fileName;
        File dir = new File(zipPath);
        synchronized (fileLock) {
            if (!dir.exists()) {//如果文件夹不存在
                dir.mkdir();//创建文件夹
            }
        }
        DocumentUtil.compress(documentPath,destPath);
        /*******************以上为获取项目路径并进行文件压缩内容************************/
        String path = destPath;
        File file = new File(path);
        InputStream ins = new FileInputStream(file);
        /* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
        response.setContentType("multipart/form-data");
        /* 设置文件头：最后一个参数是设置下载文件名 */
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));

        //下载内容
        OutputStream os = response.getOutputStream();
        try {
            byte[] b = new byte[1024];
            int len;
            while ((len = ins.read(b)) > 0) {
                os.write(b, 0, len);
            }
            UpdateWrapper<Homework> homeworkUpdateWrapper = new UpdateWrapper<>();
            homeworkUpdateWrapper.in("id",homeworkList.stream().map(e->e.getId()));
            homeworkUpdateWrapper.set("status",Constant.TABLE_DELETE_CODE);
            homeworkService.update(homeworkUpdateWrapper);

//            log.info("用户:" + + "==>下载作业文件：" + fileName);
        } finally {
            os.flush();
            os.close();
            ins.close();
        }
    }


}
