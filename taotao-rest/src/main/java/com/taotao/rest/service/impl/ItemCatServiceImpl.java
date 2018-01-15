package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.IItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by runa on 2018/1/15.
 */
@Service
public class ItemCatServiceImpl implements IItemCatService
{
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public CatResult getItemCatList()
    {
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return catResult;
    }
    private List<?> getCatList(long parentId)
    {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List resultList = new ArrayList<>();
        int count = 0;
        for (TbItemCat tbItemCat:list)
        {
            if (tbItemCat.getIsParent())
            {
                CatNode catNode = new CatNode();
                if (parentId == 0)
                {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                catNode.setItem(getCatList(tbItemCat.getId()));

                resultList.add(catNode);
                count++;
                if (parentId == 0 && count >=14) {
                    break;
                }
            }
            else
            {
                resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
            }
        }
        return  resultList;
    }
}
