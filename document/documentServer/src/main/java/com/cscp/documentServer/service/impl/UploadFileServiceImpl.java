package com.cscp.documentServer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cscp.common.utils.ViewException;
import com.cscp.documentServer.dao.entity.UploadFile;
import com.cscp.documentServer.dao.mapper.UploadFileMapper;
import com.cscp.documentServer.service.IUploadFileService;
import com.cscp.documentServer.support.UploadEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * <p>
 * 上传文件表 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-01-05
 */
@Service
@Slf4j
public class UploadFileServiceImpl extends ServiceImpl<UploadFileMapper, UploadFile> implements IUploadFileService {
    public static final String DOCUMENT_DIR_PATH;
    public static final String SEPARATOR;
    public static final String ROOT_FILE_NAME = "upload";

    static {
        if (System.getProperty("os.name").contains("Windows")) {
            SEPARATOR = "\\";
        } else {
            SEPARATOR = "/";
        }
        String projectPath = System.getProperty("user.dir");  //当前项目的路径
        DOCUMENT_DIR_PATH = projectPath.substring(0, projectPath.lastIndexOf(SEPARATOR)) + SEPARATOR + ROOT_FILE_NAME;
    }

    @Autowired
    UploadFileMapper uploadFileMapper;

    /***
     * @discription 上传单个文件
     * @param: [uploadEntity]
     * @return: int
     * @author: ckz
     * @date: 2020/1/5
     */
    @Override
    public String uploadFile(UploadEntity uploadEntity) {
        MultipartFile file = uploadEntity.getFile();
        String suffixes = uploadEntity.getSuffixes();
        if (file == null) {
            throw new ViewException("上传文件为空");
        }
        String originalFilename = file.getOriginalFilename();
        //检查支持格式
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (suffixes != null) {
            String[] suffixArr = suffixes.split(",");
            boolean flag = false;
            if (suffixArr.length > 0) {
                for (String aSuffixArr : suffixArr) {
                    if (aSuffixArr.equalsIgnoreCase(fileSuffix)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                throw new ViewException("上传单个文件时，文件 " + originalFilename + " ，不是支持的文件格式！");
            }
        }
        //上传文件到服务器
        String transferToDir = DOCUMENT_DIR_PATH + uploadEntity.getRelativePath();
        String transferTo = DOCUMENT_DIR_PATH + uploadEntity.getRelativePath() + SEPARATOR +LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))+originalFilename ;
        log.info(String.format("上传文件%s，上传路径%s", originalFilename, transferTo));
        if (!new File(transferToDir).exists()) {
            new File(transferToDir).mkdirs();
        }
        try {
            file.transferTo(new File(transferTo));
        } catch (IOException e) {
            throw new ViewException("上传文件失败--->>>" + e.getMessage());
        }
        //保存记录到数据库
        UploadFile uploadFile = new UploadFile();
        String id = UUID.randomUUID().toString();
        uploadFile.setId(id);
        uploadFile.setOriginName(originalFilename);
        uploadFile.setUploadTime(LocalDateTime.now());
        uploadFile.setLocation(transferTo);
        uploadFile.setContentType(file.getContentType());
        uploadFile.setSize((int) (file.getSize() / 1024));
        uploadFileMapper.insert(uploadFile);
        return id;
    }

    /***
     * @discription 上传多个文件
     * @param: [uploadEntity]
     * @return: java.util.List<java.lang.Integer>
     * @author: ckz
     * @date: 2020/1/5
     */
    @Override
    public List<String> uploadFiles(UploadEntity uploadEntity) {
        List<MultipartFile> files = uploadEntity.getFiles();
        if (CollectionUtils.isEmpty(files)) {
            throw new ViewException("请至少上传一个文件");
        }
        //检查文件类型
        if (uploadEntity.getSuffixes() != null) {
            HashSet<String> suffixSet = new HashSet<>();
            String suffixes = uploadEntity.getSuffixes();
            String[] suffixArr = suffixes.split(",");
            List<String> supportFileSuffix = Arrays.asList(suffixArr);
            files.forEach((file -> {
                String originalFilename = file.getOriginalFilename();
                suffixSet.add(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
            }));
            HashSet<String> unMatchedSuffix = new HashSet<>();
            suffixSet.forEach(s -> {
                if (!supportFileSuffix.contains(s)) {
                    unMatchedSuffix.add(s);
                }
            });
            if (!CollectionUtils.isEmpty(unMatchedSuffix)) {
                throw new ViewException("不支持的文件格式" + unMatchedSuffix);
            }
        }
        List<String> ids = new LinkedList<>();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            //上传文件到服务器
            String transferToDir = DOCUMENT_DIR_PATH + uploadEntity.getRelativePath();
            String transferTo = DOCUMENT_DIR_PATH + uploadEntity.getRelativePath() + SEPARATOR + originalFilename + LocalDateTime.now();
            log.info(String.format("上传文件%s，上传路径%s", originalFilename, transferTo));
            if (!new File(transferToDir).exists()) {
                new File(transferToDir).mkdirs();
            }
            try {
                file.transferTo(new File(transferTo));
            } catch (IOException e) {
                throw new ViewException("上传文件失败--->>>" + e.getMessage());
            }
            //保存记录到数据库
            UploadFile uploadFile = new UploadFile();
            String id = UUID.randomUUID().toString();
            uploadFile.setId(id);
            uploadFile.setOriginName(originalFilename);
            uploadFile.setLocation(transferTo);
            uploadFile.setContentType(file.getContentType());
            uploadFile.setSize((int) (file.getSize() / 1024));
            uploadFileMapper.insert(uploadFile);
            ids.add(id);
        }
        return ids;
    }
}
