package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.CommentsMapper;
import cn.edu.zust.se.service.CommentsServiceI;
import cn.edu.zust.se.vo.ActivityCommentsVo;
import cn.edu.zust.se.vo.ClubCommentsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@Service
@Transactional
public class CommentsServiceImpl implements CommentsServiceI {
    @Resource
    CommentsMapper commentsMapper;

    @Override
    public void postActivityComments(int userId, int activityId, String comments) {
        commentsMapper.insertActivityComments(userId, activityId, comments, new Date(new java.util.Date().getTime()));
    }

    @Override
    public void postClubComments(int userId, int clubId, String comments) {
        commentsMapper.insertClubComments(userId, clubId, comments, new Date(new java.util.Date().getTime()));
    }

    @Override
    public Integer getClubCommentsCountByClubId(int clubId) {
        return commentsMapper.selectClubCommentsByClubIdNumber(clubId);
    }

    @Override
    public List<ClubCommentsVo> getClubCommentsByClubId(int clubId, int pageNo, int pageSize) {
        return commentsMapper.selectClubCommentsByClubId(clubId, pageNo, pageSize);
    }

    @Override
    public Integer getActivityCommentsCountByActivityId(int activityId) {
        return commentsMapper.selectActivityCommentsByActivityIdNumber(activityId);
    }

    @Override
    public List<ActivityCommentsVo> getActivityCommentsByActivityId(int activityId, int pageNo, int pageSize) {
        return commentsMapper.selectActivityCommentsByActivityId(activityId, pageNo, pageSize);
    }
}
