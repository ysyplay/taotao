package com.taotao.service.Impl;

import com.google.common.collect.Lists;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by runa on 2018/1/9.
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService
{
    @Autowired
    private TbItemCatMapper itemCatMapper;

    public List<EUTreeNode> selectbyParentID(Long parentId)
    {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
        List<EUTreeNode> resultList = Lists.newArrayList();

        for (TbItemCat itemCatgory:itemCats)
        {
            EUTreeNode node = new EUTreeNode();
            node.setId(itemCatgory.getId());
            node.setText(itemCatgory.getName());
            node.setState(itemCatgory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }
}
