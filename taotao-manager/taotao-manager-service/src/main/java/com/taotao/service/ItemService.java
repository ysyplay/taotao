package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

/**
 * Created by runa on 2018/1/8.
 */
public interface ItemService
{
    TbItem selectById(Long itemID);
    EUDataGridResult getItemList(int page,int rows);
}
