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

    @Autowired
    IUploadFileService iUploadFileService;

    @Resource
    IShareDocumentService documentService;

    @Resource
    IShareDocumentTypeService shareDocumentTypeService;

    @Resource
    UserClient userClient;

    @Resource
    IUploadFileService uploadFileService;



    @PostMapping("/upload")
    public Result upload(MultipartFile file,Long credits,String typeId){
        ShareDocument document=new ShareDocument();
        document.setCredits(credits);
        UserDto currentUser = userClient.getCurrentUser();
        document.setTId(typeId);
        document.setUId(currentUser.getId());
        String fId = iUploadFileService.uploadFile(new UploadEntity("jpg,jpeg,png", UploadFileServiceImpl.SEPARATOR + "a", file));
        document.setFId(fId);
        document.setDocumentCount(0L);
        document.setStatus(DOCUMENT_CHECK_STATUS);
        documentService.save(document);
        return ResultUtil.success("上传成功");
    }


    @GetMapping("/check/{id}")
    public Result check(HttpServletResponse response, @PathVariable String id) throws IOException {
        UpdateWrapper<ShareDocument> documentUpdateWrapper = new UpdateWrapper<>();
        documentUpdateWrapper.eq("id", id);
        documentUpdateWrapper.set("status", DOCUMENT_NORMAL_STATUS);
        documentService.update(documentUpdateWrapper);
        return ResultUtil.success("审核通过");
    }

    @GetMapping("/download/{id}")
    @Transactional
    public void download(HttpServletResponse response, @PathVariable String id, @RequestParam int status) throws IOException, ViewException {
        //获取服务器文件
        ShareDocument document = documentService.getById(id);
        if (status == DOCUMENT_NORMAL_STATUS&&document.getStatus()!=DOCUMENT_NORMAL_STATUS){
            throw new ViewException("该文件尚未通过审核，不能下载");
        }
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

    @GetMapping("/delete/{id}")
    public Result delete( @PathVariable String id) {
        UpdateWrapper<ShareDocument> documentUpdateWrapper = new UpdateWrapper<>();
        documentUpdateWrapper.eq("id", id);
        documentUpdateWrapper.set("status", DOCUMENT_DELETE_STATUS);
        documentService.update(documentUpdateWrapper);
        return ResultUtil.success("删除成功");
    }

    @GetMapping("/reject/{id}")
    public Result reject(@PathVariable String id) {
        UpdateWrapper<ShareDocument> documentUpdateWrapper = new UpdateWrapper<>();
        documentUpdateWrapper.eq("id", id);
        documentUpdateWrapper.set("status", DOCUMENT_DELETE_STATUS);
        documentService.update(documentUpdateWrapper);
        return ResultUtil.success("操作成功");
    }

    @GetMapping("/getDownloadFiles")
    public Result getDownloadFiles(GridRequest gridRequest){
        Map map=new HashMap();
        map.put("status",DOCUMENT_NORMAL_STATUS);
        gridRequest.setFilterParams(map);
        return ResultUtil.success(documentService.getDocumentVoList(gridRequest));
    }

    @GetMapping("/getCheckedFiles")
    public Result getCheckedFiles(GridRequest gridRequest){
        Map map=new HashMap();
        map.put("status",DOCUMENT_CHECK_STATUS);
        gridRequest.setFilterParams(map);
        return ResultUtil.success(documentService.getDocumentVoList(gridRequest));
    }

    @GetMapping("/getFileTypes")
    public Result getFileTypes(){
        List<ShareDocumentType> list = shareDocumentTypeService.list();
        return ResultUtil.success(list);
    }


}
