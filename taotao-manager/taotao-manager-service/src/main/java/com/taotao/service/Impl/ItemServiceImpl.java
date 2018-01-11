package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamMapper itemParamMapper;


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
    //查询商品规格列表
    public EUDataGridResult getItemParamList(int page, int rows)
    {
        TbItemParamExample example = new TbItemParamExample();
        PageHelper.startPage(page, rows);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //查询分类下的规格模版
    public ServerResponse getItemParamByCid(long cid)
    {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if (list != null && list.size() > 0) {
            return ServerResponse.createBySuccess(list.get(0));
        }

        return ServerResponse.createBySuccess();
    }

    //添加规格
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        //补全pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入到规格参数模板表
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }

    //添加商品
    @Transactional
    public ServerResponse addItem(TbItem item,String description) throws Exception {
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        addDescription(itemId,description);
        return  ServerResponse.createBySuccess();
    }




    public void addDescription(Long itemId,String description)
    {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(description);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
    }


}
