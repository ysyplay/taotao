package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    //添加商品
    public ServerResponse addItem(TbItem item)
    {
        item.setId(IDUtils.genItemId());
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        int result = itemMapper.insert(item);
        if (result>0)
        {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }


}
