//package com.cscp.documentServer.service.impl;
//
//import com.cscp.documentServer.dao.entity.ShareDocument;
//import com.cscp.documentServer.dao.entity.UploadFile;
//import com.cscp.documentServer.service.IShareDocumentService;
//import com.cscp.documentServer.service.IUploadFileService;
//import net.minidev.json.JSONUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * @author chen kezhuo
// * @discription
// * @date 2019/8/22 - 23:27
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class DocumentServiceImplTest {
//
//    @Autowired
//    IUploadFileService uploadFileService;
//    @Autowired
//    IShareDocumentService shareDocumentService;
//
//    @Test
//    public void getAllDocuments() {
//        List<ShareDocument> list = shareDocumentService.list();
//        for (ShareDocument d :
//                list) {
//            UploadFile file = uploadFileService.getById(d.getFId());
//            if (file!=null){
//
//                d.setFName(file.getOriginName());
//                shareDocumentService.updateById(d);
//            }
//        }
//    }
//}