package cn.edu.zust.se.vo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class ClubVo {
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
