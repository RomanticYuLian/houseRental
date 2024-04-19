package com.yulian.houserental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode
public class House implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //地址
    private String address;
    //展示图片路径
    private String imageUrl;
    //类型
    private String type;
    //价格
    private String price;
    //简介
    private String about;
    //房主id,外键
    private Integer landlordId;

    private String username;
    //状态：0为下架，1为在租，默认为1
    private Integer status;


}
