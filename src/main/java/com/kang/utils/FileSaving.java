package com.kang.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileSaving {
    private final static String UploadPath="D:/cmbIntern/集卡项目/localserver/public/";

    public static String upload(MultipartFile file, String dirName){
        String fileName= UUIDGenerate.generate()+"--"+file.getOriginalFilename();
        File saveFile=new File(UploadPath+dirName+"/");
        if (!saveFile.exists()) {
            //若不存在该目录，则创建目录
            saveFile.mkdir();
        }
        try {
            //将文件保存指定目录
            file.transferTo(new File(UploadPath+dirName+"/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirName+"/"+fileName;
    }

}
