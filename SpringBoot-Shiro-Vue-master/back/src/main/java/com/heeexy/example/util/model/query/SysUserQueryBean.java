package com.heeexy.example.util.model.query;

import com.heeexy.example.util.model.utilBean.BaseQueryBean;
import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-02-05
 * @description:
 */
@Data
public class SysUserQueryBean extends BaseQueryBean {

    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 角色id
     */
    private Integer roleId;

    private String createTime;

    private String  updateTime;

    private String deleteStatus;
}
