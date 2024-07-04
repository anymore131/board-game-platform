package cn.edu.zust.se.service;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.vo.ActivityVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface ActivityServiceI {
    List<ActivityVo> getActivityVoByClubId(int id, int userId);

    Integer getActivityVoNumberByTag(String tag);

    List<ActivityVo> getActivityVoByTag(String tag, int pageNo, int pageSize, int userId);

    Integer getActivityVoNumberByName(String name);

    List<ActivityVo> getActivityVoByName(String tag, int pageNo, int pageSize, int userId);

    ActivityVo insertActivity(ActivityBo activityBo,int userId);

    void deleteActivity(int id);

    ActivityVo getActivityVoById(int id, int userId);

    void updateActivity(ActivityBo activityBo);

    List<ActivityVo> listClubActivity(int clubId);

    void userAttendActivity(int userId,int activityId);

    void userQuitActivity(int userId,int activityId);
}
