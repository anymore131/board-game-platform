package cn.edu.zust.se.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class UserAttend {
    int id;
    int userId;
    int activityId;
    Date attendTime;
    int attendType;
}
