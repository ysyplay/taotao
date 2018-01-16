package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by runa on 2018/1/16.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService
{
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId)
    {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory contentCategory : list)
        {
          EUTreeNode node = new EUTreeNode();
          node.setId(contentCategory.getId());
          node.setText(contentCategory.getName());
          node.setState(contentCategory.getIsParent()?"closed":"open");
          resultList.add(node);
        }
        return resultList;
    }

    @Override
    public ServerResponse insertContentCategory(long parentId, String name)
    {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insertSelective(contentCategory);
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentCat.getIsParent())
        {
            parentCat.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        return ServerResponse.createBySuccess(contentCategory);
    }

    @Override
    public EUDataGridResult getCategoryContentList(long catId,Integer page,Integer rows)
    {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(catId);
        PageHelper.startPage(page,rows);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        EUDataGridResult result = new EUDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
         return result;
    }
}





















