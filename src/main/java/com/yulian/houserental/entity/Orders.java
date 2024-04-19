package com.yulian.houserental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class Orders implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //地址
    private String address;
    //图片路径
    private String imageUrl;
    //类型
    private String type;
    //价格
    private String price;
    //创建时间
    private LocalDateTime createTime;
    //支付状态：0为未支付，1为已支付，默认为0
    private Integer status;
    //租户
    private Integer lesseeId;
    //房主
    private Integer houseId;
    //房主姓名
    private String landlordName;
    //租户姓名
    private String lesseeName;
}
