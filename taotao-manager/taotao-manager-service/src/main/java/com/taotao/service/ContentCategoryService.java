package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.ServerResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by runa on 2018/1/16.
 */
public interface ContentCategoryService
{
    List<EUTreeNode> getCategoryList(long parentId);
    ServerResponse insertContentCategory(long parentId, String name);
    EUDataGridResult getCategoryContentList(long catId, Integer page, Integer rows);
}
