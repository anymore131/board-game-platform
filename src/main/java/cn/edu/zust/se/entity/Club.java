package cn.edu.zust.se.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class Club {
    int id;
    String name;
    int userId;
    Date createTime;
    String province;
    String city;
    String tags;
    String introduction;
    int status;
}
