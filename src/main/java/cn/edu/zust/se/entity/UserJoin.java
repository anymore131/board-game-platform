package cn.edu.zust.se.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author Lenovo
 */
@Data
public class UserJoin {
    int id;
    int userId;
    int clubId;
    Date joinTime;
    int clubType;
}
