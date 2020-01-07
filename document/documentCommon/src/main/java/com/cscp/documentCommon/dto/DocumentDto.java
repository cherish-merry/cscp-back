package com.cscp.documentCommon.dto;

import lombok.Data;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/22 - 14:14
 */
@Data
public class DocumentDto {
    private String dId;

    private String title;

    private String author;

    private String school;

    private String category;

    private String label;

    private Integer downloadCount;

    private int good;

    private int bad;
}
