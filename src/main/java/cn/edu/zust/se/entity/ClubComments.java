package cn.edu.zust.se.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class ClubComments {
    int id;
    int userId;
    int clubId;
    String comments;
    Date commentsTime;
}
