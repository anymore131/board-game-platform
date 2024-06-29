package cn.edu.zust.se.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class ActivityComments {
    int id;
    int userId;
    int activityId;
    String comments;
    Date commentTime;
}
