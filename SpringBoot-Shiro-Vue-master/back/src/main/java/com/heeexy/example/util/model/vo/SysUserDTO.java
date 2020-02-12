package com.heeexy.example.util.model.vo;

import lombok.Data;

/**
 * @author: lipan
 * @date: 2020-02-05
 * @description:
 */
@Data
public class SysUserDTO {

    private Integer userId;
    /**
     * 用户名
     */
    private String userName;

    /**
     *
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;
}
