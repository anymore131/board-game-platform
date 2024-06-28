package cn.edu.zust.se.bo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@Data
public class ClubBo {
    int id;
    String name;
    int userId;
    Date createTime;
    String province;
    String city;
    String tags;
    String introduction;
    int status;
}
