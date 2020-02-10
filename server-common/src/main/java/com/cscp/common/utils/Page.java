package com.cscp.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

/***
 * @discription 分页参数
 * @author: cscp
 * @date: 2019/8/20
 */

@Data
public class Page {
    private int current;
    private int size;


    public static IPage getIPage(Page page){
        IPage iPage=new com.baomidou.mybatisplus.extension.plugins.pagination.Page();
        iPage.setCurrent(page.getCurrent());
        iPage.setSize(page.getSize());
        return iPage;
    }
}