package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by runa on 2018/1/8.
 */
@Controller
@RequestMapping("/product/")
public class ItemController
{
    @Autowired
    private ItemService itemService;

    @RequestMapping("item/{itemID}.do")
    @ResponseBody
    public TbItem selectbyId(@PathVariable Long itemID)
    {
        TbItem item = itemService.selectById(itemID);
        return  item;
    }

    @RequestMapping("itemlist.do")
    @ResponseBody
    public EUDataGridResult selectbyId(int page, int rows)
    {
        return  itemService.getItemList(page, rows);
    }
}
