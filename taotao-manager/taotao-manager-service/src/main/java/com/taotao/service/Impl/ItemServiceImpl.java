package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Arrays;
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
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;


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

    //删除规格
    @Transactional
    public ServerResponse deleteItemParam(String ids) {

        TbItemParamExample itemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = itemParamExample.createCriteria();
        List idList = Arrays.asList(ids.split(","));
        criteria.andIdIn(idList);
        //插入到规格参数模板表
        itemParamMapper.deleteByExample(itemParamExample);
        return ServerResponse.createBySuccess();

    }

    //添加商品
    @Transactional
    public ServerResponse addItem(TbItem item,String description,String itemParam) throws Exception {
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        addDescription(itemId,description);
        insertItemParamItem(itemId,itemParam);
        return  ServerResponse.createBySuccess();
    }



    // 添加描述信息
    public void addDescription(Long itemId,String description)
    {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(description);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
    }

    // 添加规格参数

    private void insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);
    }

}
