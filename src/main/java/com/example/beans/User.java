package com.example.beans;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 支持序列化
 * 用于缓存
 */
@Data
public class User implements Serializable {
    @SerializedName("id")
    private String userId;
    private Integer age;
    private String gender;
}
