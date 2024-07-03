package cn.edu.zust.se.service;

import cn.edu.zust.se.vo.ActivityCommentsVo;
import cn.edu.zust.se.vo.ClubCommentsVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface CommentsServiceI {


    void postClubComments(int userId, int clubId, String comments);

    Integer getClubCommentsCountByClubId(int clubId);

    List<ClubCommentsVo> getClubCommentsByClubId(int clubId, int pageNo, int pageSize);
    void postActivityComments(int userId, int activityId, String comments);

    Integer getActivityCommentsCountByActivityId(int activityId);

    List<ActivityCommentsVo> getActivityCommentsByActivityId(int activityId, int pageNo, int pageSize);
}