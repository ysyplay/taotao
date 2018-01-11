package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;

/**
 * Created by runa on 2018/1/8.
 */
public interface ItemService
{
    TbItem selectById(Long itemID);
    EUDataGridResult getItemList(int page,int rows);
    ServerResponse addItem(TbItem item,String description) throws Exception;
    EUDataGridResult getItemParamList(int page,int rows);
    ServerResponse getItemParamByCid(long cid);
    TaotaoResult insertItemParam(TbItemParam itemParam);
}
