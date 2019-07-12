package com.xlx.controller;

import com.xlx.util.FileUtils;
import com.xlx.util.ReMessage;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/10 18:38
 * @Description:
 */

@RequestMapping("file")
@Controller
@Slf4j
public class FileController {

    private String filePath = "E:\\file";

    @ResponseBody
    @ApiOperation(value = "文件上传接口", notes = "文件上传", httpMethod = "POST", response = ReMessage.class)
    @RequestMapping(value = "upload" , method = RequestMethod.POST)
    public ReMessage upload(HttpServletRequest request) {
        ReMessage reMessage = new ReMessage();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        boolean flag = false;
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
            for (FileItem fileItem : items) {
                inputStream = fileItem.getInputStream();
                FileUtils.write(inputStream, "E:\\file\\test" + File.separator + fileItem.getName());
            }
            flag = true;
            reMessage.setCode(0);
        } catch (Throwable e) {
            log.warn("fail to upload file -- " + e);
            flag = false;
            reMessage.setCode(1);
        }
        reMessage.setSuccess(flag);
        return reMessage;
    }

    @ResponseBody
    @ApiOperation(value = "文件下载接口", notes = "文件下载", httpMethod = "GET", response = byte.class)
    @RequestMapping(value = "download" , method = RequestMethod.GET)
    public byte[] download(@RequestParam("fileName") String fileName){
        String filepath = filePath + File.separator + fileName;
        return FileUtils.getByte(filepath);
    }

    @RequestMapping(value = "delete" , method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "文件删除接口", notes = "文件删除", httpMethod = "DELETE", response = String.class)
    public String delete(@RequestParam("fileName") String fileName){
        File file = new File(filePath + File.separator +fileName);
        if(!file.exists()){
            return "1";
        }
        if(file.delete()){
            return "0";
        }
        else {
            return "1";
        }

    }


}
