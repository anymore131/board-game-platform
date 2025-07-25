package cn.edu.zust.se.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@Data
public class ActivityVo {

    int id;
    int clubId;
    String clubName;
    String activityName;
    String introduction;
    List<String> tags;
    String address;
    Date startTime;
    Date endTime;
    Date createTime;
    int number;
    //1为已参加，0为未参加
    int attended;
    //1为管理，0为普通成员
    int clubType;
}
