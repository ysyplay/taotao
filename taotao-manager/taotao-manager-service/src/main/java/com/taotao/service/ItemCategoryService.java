package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by runa on 2018/1/9.
 */
public interface ItemCategoryService
{
    List<EUTreeNode> selectbyParentID(Long parentId);
}
