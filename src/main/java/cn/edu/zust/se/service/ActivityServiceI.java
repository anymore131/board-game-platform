package cn.edu.zust.se.service;

import cn.edu.zust.se.vo.ActivityVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface ActivityServiceI {
    List<ActivityVo> getActivityVoById(int id,int userId);
    Integer getActivityVoNumberByTag(String tag);
    List<ActivityVo> getActivityVoByTag(String tag,int pageNo,int pageSize,int userId);
    Integer getActivityVoNumberByName(String name);
    List<ActivityVo> getActivityVoByName(String tag,int pageNo,int pageSize,int userId);
}
