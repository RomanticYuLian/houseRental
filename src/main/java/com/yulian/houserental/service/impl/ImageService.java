package com.yulian.houserental.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${download.address}")
    private String address;
    @Value("${service.ipAddr}")
    private String ip;

    public String uploadImage(MultipartFile file) {
        OutputStream os = null;
        InputStream is = null;
        String newName=getNewName(file);
        try {
            is=file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bs=new byte[1024];
            int length;
            File tempFile = new File(address);
            if (!tempFile.exists()){
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath().concat(File.separator).concat(newName));
            while ((length = is.read(bs)) != -1) {
                os.write(bs, 0, length);
            }
            return ip.concat("/img/").concat(newName);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                is.close();
            }catch (Exception e){

            }
        }
        return null;
        }
    public String getNewName(MultipartFile file){
        String uuid= UUID.randomUUID().toString();
        String filename=file.getOriginalFilename();
        String newName=uuid+filename.substring(filename.lastIndexOf("."));
        return newName;
    }
}
