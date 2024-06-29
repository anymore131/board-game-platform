package cn.edu.zust.se.vo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class ActivityCommentsVo {
    int id;
    int userId;
    int activityId;
    String userName;
    String activityName;
    String comments;
    Date commentTime;
}
