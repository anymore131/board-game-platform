package cn.edu.zust.se.service;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.vo.ActivityVo;
import cn.edu.zust.se.vo.UserVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface ActivityServiceI {
    List<ActivityVo> getActivityVoByClubId(int id, int userId);

    Integer getActivityVoNumberByTag(String tag);

    List<ActivityVo> getActivityVoByTag(String tag, int pageNo, int pageSize, int userId);

    Integer getActivityVoNumberByName(String name);

    List<ActivityVo> getActivityVoByName(String name, int pageNo, int pageSize, int userId);

    ActivityVo insertActivity(ActivityBo activityBo,int userId);

    void deleteActivity(int id);

    ActivityVo getActivityVoById(int id, int userId);

    ActivityVo getActivityById(int id);

    List<UserVo> getUserAttend(int activityId);

    void updateActivity(ActivityBo activityBo);

    List<ActivityVo> listClubActivity(int clubId,int userId);

    void userAttendActivity(int userId,int activityId);

    void userQuitActivity(int userId,int activityId);

    Integer getActivityUnStartNumberByUserId(int userId);
    //找到用户参加的还没有开始的活动
    List<ActivityVo> getActivityUnStartByUserId(int userId,int pageNo, int pageSize);

    Integer getActivityStartingNumberByUserId(int userId);
    //找到用户参加的真正进行的活动
    List<ActivityVo> getActivityStartingByUserId(int userId,int pageNo, int pageSize);

    Integer getActivityEndNumberByUserId(int userId);
    //找到用户参加过的已经结束的活动
    List<ActivityVo> getActivityEndByUserId(int userId,int pageNo, int pageSize);

    Integer getActivityUnStartNumberByClubId(int clubId);
    //找到俱乐部还没有开始的活动
    List<ActivityVo> getActivityUnStartByClubId(int clubId,int userId,int pageNo, int pageSize);

    Integer getActivityStartingNumberByClubId(int clubId);
    //找到用户参加的真正进行的活动
    List<ActivityVo> getActivityStartingByClubId(int clubId,int userId,int pageNo, int pageSize);

    Integer getActivityEndNumberByClubId(int clubId);
    //找到用户参加过的已经结束的活动
    List<ActivityVo> getActivityEndByClubId(int clubId,int userId,int pageNo, int pageSize);

}
