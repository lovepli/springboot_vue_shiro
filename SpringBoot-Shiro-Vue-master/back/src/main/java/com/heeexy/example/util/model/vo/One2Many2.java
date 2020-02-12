package com.heeexy.example.util.model.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author: lipan
 * @date: 2020-02-07
 * @description:
 */
@Data
public class One2Many2 extends UserPermissionDTO {

    public Set<String> roleList;
    public Set<String> menuList;
    public Set<String> permissionList;
    public Set<Integer> permissionIds;
    public List<JSONObject> picList;
    public List<JSONObject> menus;
    public List<JSONObject> users;
    public List<JSONObject> permissions;
}
