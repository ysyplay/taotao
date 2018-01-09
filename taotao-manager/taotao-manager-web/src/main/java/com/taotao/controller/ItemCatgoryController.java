package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by runa on 2018/1/9.
 */
@Controller
@RequestMapping("/item")
public class ItemCatgoryController
{
    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/cat/list")
    @ResponseBody
    public List<EUTreeNode> selectCatgorybyParentId(@RequestParam(value = "id",defaultValue = "0") long parentId)
    {
        return  itemCategoryService.selectbyParentID(parentId);
    }
}
