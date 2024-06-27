package cn.edu.zust.se.bo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class UserBo {
    int id;
    //真实姓名
    String name;
    //用户名
    String userName;
    //密码
    String password;
    int age;
    //性别
    int gender;
    String introduction;
    //权限
    int type;
    String email;
    String mobile;
    //省
    String province;
    //城市
    String city;
    //注册时间
    Date registerTime;
    String QQ;
    String weixin;
    //头像
    String avatarFname;
    //在线状态
    int status;
}
