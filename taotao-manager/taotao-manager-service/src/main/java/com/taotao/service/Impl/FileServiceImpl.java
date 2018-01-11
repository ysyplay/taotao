package com.taotao.service.Impl;

import com.google.common.collect.Maps;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.IFileService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by runa on 2018/1/10.
 */
@Service
public class FileServiceImpl implements IFileService
{
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    public HashMap upload(MultipartFile file)
    {
        HashMap resultMap = Maps.newHashMap();
        //获得文件名
        String oldName = file.getOriginalFilename();
        //生成新的文件名
        String newName = IDUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));
        //按日归类文件的文件夹
        String imagePath = new DateTime().toString("/yyyy/MM/dd");
        //上传Ftp服务器
        try
        {
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_BASE_PATH,imagePath,newName,file.getInputStream());
            if (result)
            {
                resultMap.put("error", 0);
                resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
                return resultMap;
            }
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }
}
