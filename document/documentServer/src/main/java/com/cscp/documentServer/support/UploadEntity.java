package com.cscp.documentServer.support;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/1/5 - 14:47
 */
@Data
public class UploadEntity {
    private String suffixes;
    private String relativePath;
    private List<MultipartFile> files;
    private MultipartFile file;

    public UploadEntity(String suffixes, String relativePath, MultipartFile file) {
        this.suffixes = suffixes;
        this.relativePath = relativePath;
        this.file = file;
    }

    public UploadEntity(String suffixes, String relativePath, List<MultipartFile> files) {
        this.suffixes = suffixes;
        this.relativePath = relativePath;
        this.files = files;
    }
}
