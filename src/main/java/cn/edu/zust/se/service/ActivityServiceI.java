package cn.edu.zust.se.service;

import cn.edu.zust.se.vo.ActivityVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface ActivityServiceI {
    List<ActivityVo> getActivityVoById(int id,int userId);
    int getActivityVoNumberByTag(String tag);
    List<ActivityVo> getActivityVoByTag(String tag,int pageNo,int pageSize,int userId);
    int getActivityVoNumberByName(String name);
    List<ActivityVo> getActivityVoByName(String tag,int pageNo,int pageSize,int userId);
}
