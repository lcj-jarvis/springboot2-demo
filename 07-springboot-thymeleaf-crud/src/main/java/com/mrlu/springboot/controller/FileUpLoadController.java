package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.Person;
import com.mrlu.springboot.utils.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-17 21:27
 *
 * 文件上传测试
 */
@Slf4j
@Controller
public class FileUpLoadController {

    @GetMapping("/form_layouts")
    public String formLayouts(){

        return "form/form_layouts";
    }

    /**
     * MultipartFile 自动封装了上传过来的文件。
     * 可以在yaml文件中配置文件上传的配置
     * spring:
     *   servlet:
     *     multipart:
     *       max-file-size: 10MB   单个文件上传的最大大小
     *       max-request-size: 100MB  所有请求的总文件的上传大小
     * @param headerImg 封装了单个文件
     * @param photos  封装了多个文件.封装到MultipartFile[]数组中获取List<MultipartFile>中都行
     * @return
     */
    @PostMapping("/upload")
    public String upLoad(@RequestParam("email")String email,
                         @RequestParam("username")String username,
                         @RequestPart("headerImg")MultipartFile headerImg,
                         @RequestPart("photos")MultipartFile[] photos) throws IOException {
        log.info("上传的信息：email={},username={},headerImg={},photos={}",
                email,username,headerImg.getSize(),photos.length);
        if(!headerImg.isEmpty()){
            //保存到文件服务器，OSS服务器
            String originalFilename = headerImg.getOriginalFilename();
            //将文件保存到以下的位置
            String str = Base64Util.encode(headerImg.getBytes());
            log.info("Base64={}",Base64Util.encode(headerImg.getBytes()));
            System.out.println();
            headerImg.transferTo(new File("D:\\软件\\Cache\\springboot-fileupload\\"+originalFilename));
        }
        if (photos.length>0){
            for (MultipartFile photo:photos) {
                String originalFilename = photo.getOriginalFilename();
                //将文件保存到以下的位置
                photo.transferTo(new File("D:\\软件\\Cache\\springboot-fileupload\\"+originalFilename));
            }
        }
        return "main";
    }

    /*@PostMapping("/upload")
    public String upLoad(Person person,
                         @RequestPart("headerImg")MultipartFile headerImg,
                         @RequestPart("photos") List<MultipartFile> photos){
        System.out.println(person);
        System.out.println(headerImg.getOriginalFilename());
        System.out.println(photos.get(0).getOriginalFilename());
        return "main";
    }*/
}
