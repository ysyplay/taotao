package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //查询商品列表
    public EUDataGridResult getItemList(int page, int rows)
    {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
