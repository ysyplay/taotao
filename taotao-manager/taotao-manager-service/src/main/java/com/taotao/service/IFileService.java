package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * Created by runa on 2018/1/10.
 */
public interface IFileService
{
    HashMap upload(MultipartFile file);
}
