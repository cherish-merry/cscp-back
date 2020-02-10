package com.cscp.common.utils;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/8/20 - 19:55
 */
public class Constant {
    public static final int SUCCESS_CODE=0;
    public static final int FAILURE_CODE=100;
    public static final int TABLE_NORMAL_CODE=1;
    public static final int TABLE_DELETE_CODE=0;
    public static final String HOMEWORK_DIR_NAME = "homeworks";
    public static final String SEPARATOR;
    public static final String HOMEWORKZIP_DIR_NAME="homeworkZIP";
    public static final String DOCUMENT_DIR_PATH;
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

}
