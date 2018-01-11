package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemCategoryService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by runa on 2018/1/8.
 */
@Controller
@RequestMapping("/item/")
public class ItemController
{
    @Autowired
    private ItemService itemService;



    @RequestMapping("{itemID}")
    @ResponseBody
    public TbItem selectbyId(@PathVariable Long itemID)
    {
        TbItem item = itemService.selectById(itemID);
        return  item;
    }

    @RequestMapping("list")
    @ResponseBody
    public EUDataGridResult selectbyId(int page, int rows)
    {
        return  itemService.getItemList(page, rows);
    }





    @RequestMapping("save")
    @ResponseBody
    public ServerResponse addItem(TbItem item,String desc,String itemParams) throws Exception

    {
        return  itemService.addItem(item,desc,itemParams);
    }



}
