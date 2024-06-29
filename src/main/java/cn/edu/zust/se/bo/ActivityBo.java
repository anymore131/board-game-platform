package cn.edu.zust.se.bo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class ActivityBo {
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
