package cn.edu.zust.se.vo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class UserJoinVo {
    int id;
    int userId;
    String userName;
    int clubId;
    String clubName;
    Date joinTime;
    int clubType;
}
