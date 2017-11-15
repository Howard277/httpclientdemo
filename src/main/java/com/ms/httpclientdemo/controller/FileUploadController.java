package com.ms.httpclientdemo.controller;

import com.ms.httpclientdemo.service.BasicService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by wuketao on 2017/11/15.
 */
@Slf4j
@Controller
@RequestMapping(path = "fileupload")
public class FileUploadController {

    @Autowired
    private BasicService basicService;

    @ApiOperation("上传文件")
    @ResponseBody
    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public Integer Upload(HttpServletRequest request) {
        Integer count = 0;
        //将request进行转换
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //遍历获取所有文件
        for (Iterator<String> fileNameIterator = multipartRequest.getFileNames();
             fileNameIterator.hasNext(); ) {
            //获取文件名
            String fileName = fileNameIterator.next();
            //根据文件名获取文件对象并保存
            MultipartFile multipartFile = multipartRequest.getFile(fileName);
            try {
                multipartFile.transferTo(new File(basicService.getDownloadPath() + "\\" + fileName));
            } catch (IOException e) {
                log.error("file save error {}", e);
            }
        }
        return count;
    }
}
