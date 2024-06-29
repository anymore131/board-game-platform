package cn.edu.zust.se.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class Activity {
    int id;
    int clubId;
    String activityName;
    String introduction;
    String tags;
    String address;
    Date startTime;
    Date endTime;
    Date createTime;
}
