package com.yulian.houserental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)//只比较子类属性，默认为false
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //id主键
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //帐号状态：1为正常，0为封禁，默认为1
    private Integer active;
    //联系电话
    private String telephone;
    //角色：0为管理员，1为房主，2为普通用户
    private Integer role;
    //创建时间
    private LocalDate createTime;
    //头像图片路径
    private String imageUrl;


}
