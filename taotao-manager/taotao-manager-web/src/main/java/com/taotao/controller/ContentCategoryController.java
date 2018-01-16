package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by runa on 2018/1/16.
 */
@Controller
@RequestMapping("/content/")
public class ContentCategoryController
{
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("category/list/")
    @ResponseBody
    public List<EUTreeNode> getCategoryList(@RequestParam(value = "id",defaultValue = "0") long parentId)
    {
        return contentCategoryService.getCategoryList(parentId);
    }

    @RequestMapping("category/create")
    @ResponseBody
    public ServerResponse createContentCategory(Long parentId, String name) {
        ServerResponse result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getCategoryContentList(long catId, Integer page, Integer rows) {
        EUDataGridResult result = contentCategoryService.getCategoryContentList(catId, page, rows);
        return result;
    }


}
