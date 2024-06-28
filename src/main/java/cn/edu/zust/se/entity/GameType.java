package cn.edu.zust.se.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class GameType {
    int id;
    String name;
    String referrals;
    String introduction;
    Date insertTime;
    int userId;
    int type;
}
