package com.taotao.rest.controller;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.IItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by runa on 2018/1/15.
 */
@Controller
public class ItemCatController
{
     @Autowired
    private IItemCatService itemCatService;

     @RequestMapping("/itemcat/list")
     @ResponseBody
     public Object getItemCatList(String callback)
     {
         System.out.print("**********");
         CatResult catResult = itemCatService.getItemCatList();
         MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
         mappingJacksonValue.setJsonpFunction(callback);
         return mappingJacksonValue;
     }

}
