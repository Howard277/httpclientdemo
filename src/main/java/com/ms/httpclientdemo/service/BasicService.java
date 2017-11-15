package com.ms.httpclientdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by wuketao on 2017/11/15.
 */
@Slf4j
@Service
public class BasicService {
    /**
     * 文件保存路径
     */
    private String downloadPath;

    /**
     * 获取文件保存路径
     * @return
     */
    public String getDownloadPath(){
        return downloadPath;
    }

    /**
     * 初始化
     */
    @PostConstruct
    private void initPath() {
        mkdirDownload();
        mkdirLogs();
    }

    /**
     * 创建download路径
     */
    private void mkdirDownload() {
        //获取文件临时路径，
        downloadPath = System.getProperty("user.dir") + "\\download\\";
        File folder = new File(downloadPath);
        //启动时删除可能存在的旧数据
        if (folder.exists()) {
            deleteAllFilesOfDir(folder);
        }
        folder.mkdir();
    }

    /**
     * 删除所有文件及目录
     *
     * @param path
     */
    public void deleteAllFilesOfDir(File path) {
        try {
            if (!path.exists())
                return;

            if (path.isFile()) {
                path.delete();
            } else {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir(files[i]);
                }
                path.delete();
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
    }

    /**
     * 删除所有文件及目录
     * @param path
     */
    public void deleteAllFilesOfDir(String path){
        deleteAllFilesOfDir(new File(path));
    }

    /**
     * 创建日志路径
     */
    private void mkdirLogs() {
        //获取文件临时路径，
        String logFolder = System.getProperty("user.dir") + "\\logs\\";
        File folder = new File(logFolder);
        //启动时判断是非有日志路径，没有就创建
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}
