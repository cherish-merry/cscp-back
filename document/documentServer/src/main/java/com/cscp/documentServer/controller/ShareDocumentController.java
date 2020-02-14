package com.cscp.documentServer.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.ViewException;
import com.cscp.documentServer.client.UserClient;
import com.cscp.documentServer.dao.entity.ShareDocument;
import com.cscp.documentServer.dao.entity.ShareDocumentType;
import com.cscp.documentServer.dao.entity.UploadFile;
import com.cscp.documentServer.service.IShareDocumentService;
import com.cscp.documentServer.service.IShareDocumentTypeService;
import com.cscp.documentServer.service.IUploadFileService;
import com.cscp.documentServer.service.impl.UploadFileServiceImpl;
import com.cscp.documentServer.support.UploadEntity;
import dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
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

import static com.cscp.common.utils.Constant.SEPARATOR;
import static com.cscp.documentCommon.Constant.*;

/**
 * <p>
 * 共享文档 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/sharedocument")
@Log4j2
public class ShareDocumentController {

    @Resource
    IShareDocumentService documentService;

    @Resource
    IShareDocumentTypeService shareDocumentTypeService;

    @Resource
    UserClient userClient;

    @Resource
    IUploadFileService uploadFileService;



    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result upload(MultipartFile file,Long credits,String typeId){
        ShareDocument document=new ShareDocument();
        document.setCredits(credits);
        UserDto currentUser = userClient.getCurrentUser();
        document.setTId(typeId);
        document.setUId(currentUser.getId());
        String fId = uploadFileService.uploadFile(new UploadEntity("jpg,jpeg,png", SEPARATOR + "share_files", file));
        document.setFId(fId);
        document.setDocumentCount(0L);
        document.setStatus(DOCUMENT_CHECK_STATUS);
        documentService.save(document);
        return ResultUtil.success("上传成功");
    }

    @ApiOperation("通过文件审核")
    @GetMapping("/check/{id}")
    public Result check(HttpServletResponse response, @PathVariable String id) throws IOException {
        UpdateWrapper<ShareDocument> documentUpdateWrapper = new UpdateWrapper<>();
        documentUpdateWrapper.eq("id", id);
        documentUpdateWrapper.set("status", DOCUMENT_NORMAL_STATUS);
        documentService.update(documentUpdateWrapper);
        return ResultUtil.success("审核通过");
    }


    @ApiOperation("文件下载前处理(扣除下载积分，需传文件id)")
    @GetMapping("/preDownload/{id}")
    @Transactional
    public Result preDownload( @PathVariable String id){
        //获取服务器文件
        ShareDocument document = documentService.getById(id);
        Long credits = document.getCredits();
        UserDto master = userClient.getUserById(document.getUId());
        UserDto customer = userClient.getCurrentUser();
        customer.setCredits(customer.getCredits()-credits);
        master.setCredits(master.getCredits()+new Double(credits*0.8).longValue());
        return ResultUtil.success();
    }

    @ApiOperation("文件下载")
    @GetMapping("/download/{id}")
    @Transactional
    public void download(HttpServletResponse response, @PathVariable String id, @RequestParam int status) throws IOException, ViewException {
        //获取服务器文件
        ShareDocument document = documentService.getById(id);
        UploadFile uploadFile=uploadFileService.getById(document.getFId());
        String path = uploadFile.getLocation();
        File file = new File(path);
        InputStream ins = new FileInputStream(file);
        /* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
        response.setContentType("multipart/form-data");
        /* 设置文件头：最后一个参数是设置下载文件名 */
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(uploadFile.getOriginName(), "utf-8"));

        //下载内容
        OutputStream os = response.getOutputStream();
        try {
            byte[] b = new byte[1024];
            int len;
            while ((len = ins.read(b)) > 0) {
                os.write(b, 0, len);
            }
            if (status == DOCUMENT_NORMAL_STATUS) {
                UpdateWrapper<ShareDocument> documentUpdateWrapper = new UpdateWrapper<>();
                documentUpdateWrapper.eq("id", id);
                documentUpdateWrapper.set("document_count", document.getDocumentCount() + 1);
                documentService.update(documentUpdateWrapper);
            }else if(status==DOCUMENT_CHECK_STATUS){
            }
        } finally {
            os.flush();
            os.close();
            ins.close();
        }
    }

    @ApiOperation("文件删除")
    @GetMapping("/delete/{id}")
    public Result delete( @PathVariable String id) {
        UpdateWrapper<ShareDocument> documentUpdateWrapper = new UpdateWrapper<>();
        documentUpdateWrapper.eq("id", id);
        documentUpdateWrapper.set("status", DOCUMENT_DELETE_STATUS);
        documentService.update(documentUpdateWrapper);
        return ResultUtil.success("删除成功");
    }

    @ApiOperation("否决文件审核")
    @GetMapping("/reject/{id}")
    public Result reject(@PathVariable String id) {
        UpdateWrapper<ShareDocument> documentUpdateWrapper = new UpdateWrapper<>();
        documentUpdateWrapper.eq("id", id);
        documentUpdateWrapper.set("status", DOCUMENT_DELETE_STATUS);
        documentService.update(documentUpdateWrapper);
        return ResultUtil.success("操作成功");
    }

    @ApiOperation("获取可下载文件列表")
    @GetMapping("/getDownloadFiles")
    public Result getDownloadFiles(GridRequest gridRequest){
        Map map=new HashMap();
        map.put("status",DOCUMENT_NORMAL_STATUS);
        gridRequest.setFilterParams(map);
        return ResultUtil.success(documentService.getDocumentVoList(gridRequest));
    }

    @ApiOperation("获取需审核文件列表")
    @GetMapping("/getCheckedFiles")
    public Result getCheckedFiles(GridRequest gridRequest){
        Map map=new HashMap();
        map.put("status",DOCUMENT_CHECK_STATUS);
        gridRequest.setFilterParams(map);
        return ResultUtil.success(documentService.getDocumentVoList(gridRequest));
    }

    @ApiOperation("获取文件类型")
    @GetMapping("/getFileTypes")
    public Result getFileTypes(){
        List<ShareDocumentType> list = shareDocumentTypeService.list();
        return ResultUtil.success(list);
    }


}
