package com.cscp.documentCommon.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by 11727 on 2020/2/6 15:15
 */
@Data
public class DocumentVo {

    /**
     * 文档id
     */
    private String id;

    private String uId;

    private String userName;

    private String tId;

    private String type;

    private Integer status;

    private Long documentCount;

    private Long credits;

    private String fId;

    private String sId;

    private String sName;

    private String fName;

    private LocalDateTime uploadTime;

}
