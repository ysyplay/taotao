package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ysyplay on 2018/1/11.
 */
@Controller
public class ItemParaController
{
    @Autowired
    private ItemService itemService;


    @RequestMapping("item/param/list")
    @ResponseBody
    public EUDataGridResult selectParamLlist(int page, int rows)
    {
        return  itemService.getItemParamList(page, rows);
    }

    @RequestMapping("item/param/query/itemcatid/{itemCatId}")
    @ResponseBody
    public ServerResponse getItemParamByCid(@PathVariable Long itemCatId) {
        ServerResponse result = itemService.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping("item/param/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
        //创建pojo对象
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        TaotaoResult result = itemService.insertItemParam(itemParam);
        return result;
    }

    @RequestMapping("/item/param/delete")
    @ResponseBody
    public ServerResponse deleteItemParam(String ids)
    {
        ServerResponse result = itemService.deleteItemParam(ids);
        return result;
    }
}
