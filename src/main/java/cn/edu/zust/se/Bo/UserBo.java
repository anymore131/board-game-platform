package cn.edu.zust.se.Bo;

import lombok.Data;

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
    String gender;
    String introduction;
    //权限
    int type;
    String email;
    String mobile;
    //省
    String province;
    //城市
    String city;
    //具体地址
    String address;
    String QQ;
    String weixin;
    //在线状态
    int status;
}
