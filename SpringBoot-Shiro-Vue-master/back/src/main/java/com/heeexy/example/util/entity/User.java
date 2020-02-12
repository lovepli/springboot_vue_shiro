package com.heeexy.example.util.entity;


import io.swagger.annotations.*;
import lombok.Data;

/**
 * @author itguang
 * @create 2017-12-30 14:39
 *
 *
 * PS：用户model使用了lombok、jpa、validator，只需要关注@Api开头的注解就行了。
 **/

@Data   //lombok插件
//@Entity(name = "users")  //jpa
@ApiModel(description = "用户Model")
public class User {

    /**
     *
     此注解可以作用在字段或者方法上,只要 hidden 属性为 true ,该字段或者方法就不会被生成api文档.
     */
    @ApiModelProperty(hidden = true)
    private String id;

   // @NotBlank(message = "   用户名不能为空")  //使用的是validator插件
    @ApiModelProperty(value = "用户名",name = "username")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(hidden = true)
    private Integer age;

    @ApiModelProperty(hidden = true)
    private Boolean enabled;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
