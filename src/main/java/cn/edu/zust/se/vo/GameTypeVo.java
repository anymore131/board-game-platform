package cn.edu.zust.se.vo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class GameTypeVo {
    int id;
    String name;
    String referrals;
    String introduction;
    Date insertTime;
    String userName;
    int type;
}
