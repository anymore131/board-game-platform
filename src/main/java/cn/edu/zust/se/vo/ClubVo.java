package cn.edu.zust.se.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@Data
public class ClubVo {
    int id;
    String clubName;
    int userId;
    String userName;
    Date createTime;
    String province;
    String city;
    List<String> tags;
    String introduction;
    Date joinTime;
    int number;
    //1为已加入，0为未加入
    int joined;
}
