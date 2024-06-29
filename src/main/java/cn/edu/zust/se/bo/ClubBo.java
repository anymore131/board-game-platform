package cn.edu.zust.se.bo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class ClubBo {
    int id;
    String clubName;
    int userId;
    Date createTime;
    String province;
    String city;
    String tags;
    String introduction;
    int status;
}
