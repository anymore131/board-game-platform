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
    String clubName;
    String name;
    String introduction;
    List<String> tags;
    String address;
    Date startTime;
    Date endTime;
    Date createTime;
    int number;
}
