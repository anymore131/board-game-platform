package cn.edu.zust.se.vo;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class ClubCommentsVo {
    int id;
    int userId;
    int clubId;
    String userName;
    String clubName;
    String comments;
    Date commentsTime;
}
