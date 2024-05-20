package com.naic.controller;

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import com.naic.common.Locationzy;
import com.naic.common.Result;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileuploadController {
    @SneakyThrows
    @PostMapping("/upload")
    public Result<?> Upload(@RequestParam MultipartFile file){
        String Filename=file.getOriginalFilename();
        String hostname= Locationzy.qnhostname;
        String uuid = IdUtil.fastSimpleUUID();
        //拼接路径
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = Locationzy.akey;
        String secretKey = Locationzy.skey;
        String bucket =Locationzy.bu;
        byte[] uploadBytes = file.getBytes();

        String key = Filename;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return Result.success(hostname+putRet.key);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }

        return null;
    }
    @PostMapping("/uploadusertx")
    public Result<?> Uploadusertx(@RequestParam MultipartFile file,@RequestParam String u){
        String Filename=file.getOriginalFilename();
        String hostname= Locationzy.qnhostname;
        String uuid = IdUtil.fastSimpleUUID();
        //拼接路径
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = Locationzy.akey;
        String secretKey = Locationzy.skey;
        String bucket =Locationzy.bu;
        byte[] uploadBytes = new byte[0];
        try {
            uploadBytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String key = u+".png";
        System.out.println(key);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket,key);
        System.out.println(upToken);
        try {
            Response response = uploadManager.put(uploadBytes,key,upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return Result.success(hostname+putRet.key);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }

        return null;
    }
}
