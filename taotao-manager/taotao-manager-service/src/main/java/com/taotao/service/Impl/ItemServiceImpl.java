package com.taotao.service.Impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by runa on 2018/1/8.
 */
@Service
public class ItemServiceImpl implements ItemService
{
    @Autowired
    private TbItemMapper itemMapper;


    public TbItem selectById(Long itemID)
    {
        TbItem item = itemMapper.selectByPrimaryKey(itemID);
        return item;
    }
}
