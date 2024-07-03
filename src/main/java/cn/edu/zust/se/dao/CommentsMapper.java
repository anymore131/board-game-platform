package cn.edu.zust.se.dao;

import cn.edu.zust.se.vo.ActivityCommentsVo;
import cn.edu.zust.se.vo.ClubCommentsVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
public interface CommentsMapper {
    /**
     * 添加活动评论
     * @param userId        用户id
     * @param activityId    活动id
     * @param comments      评论
     * @param commentsTime  评论时间
     */
    @Insert("INSERT INTO board_game_platform.t_activity_comments " +
            "(user_id, activity_id, comments, comments_time) " +
            "VALUES (#{userId}, #{activityId}, #{comments}, #{commentsTime})")
    void insertActivityComments(@Param("userId") int userId,
                                @Param("activityId") int activityId,
                                @Param("comments") String comments,
                                @Param("commentsTime") Date commentsTime);

    /**
     * 添加俱乐部评论
     * @param userId        用户id
     * @param clubId        俱乐部id
     * @param comments      评论内容
     * @param commentsTime  评论时间
     */
    @Insert("INSERT INTO board_game_platform.t_club_comments " +
            "(user_id, club_id, comments, comments_time) " +
            "VALUES (#{userId}, #{clubId}, #{comments}, #{commentsTime})")
    void insertClubComments(@Param("userId") int userId,
                            @Param("clubId") int clubId,
                            @Param("comments") String comments,
                            @Param("commentsTime") Date commentsTime);

    /**
     * 通过活动id寻找评论数量
     * @param activityId    活动id
     * @return              评论数量
     */
    @Select("select count(*) " +
            "from board_game_platform.t_activity_comments " +
            "where activity_id = #{activityId}")
    int selectActivityCommentsByActivityIdNumber(@Param("activityId") int activityId);

    /**
     * 通过活动id寻找所有评论，按页分割
     * @param activityId    活动id
     * @param pageNo        页码
     * @param pageSize      每页数量
     * @return              评论列表
     */
    @Select("select c.id,c.user_id,c.activity_id,u.user_name,a.activity_name,c.comments,c.comments_time " +
            "from board_game_platform.t_activity_comments c,board_game_platform.t_user u ,board_game_platform.t_activity a " +
            "where c.activity_id = #{activityId} and c.user_id = u.id and c.activity_id = a.id " +
            "Limit #{pageSize} offset ${(pageNo - 1) * pageSize}")
    List<ActivityCommentsVo> selectActivityCommentsByActivityId(@Param("activityId") int activityId,
                                                                @Param("pageNo") int pageNo,
                                                                @Param("pageSize") int pageSize);

    /**
     * 通过俱乐部id寻找评论数量
     * @param clubId    俱乐部id
     * @return          评论数量
     */
    @Select("select count(*) " +
            "from board_game_platform.t_club_comments " +
            "where club_id = #{clubId}")
    int selectClubCommentsByClubIdNumber(@Param("clubId") int clubId);

    /**
     * 通过俱乐部id寻找所有评论，按页分割
     * @param clubId    俱乐部id
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @return          评论列表
     */
    @Select("select c.id,c.user_id,c.club_id,u.user_name,cl.club_name,c.comments,c.comments_time " +
            "from board_game_platform.t_club_comments c,board_game_platform.t_user u ,board_game_platform.t_club cl " +
            "where c.club_id = #{clubId} and c.user_id = u.id and c.club_id = cl.id " +
            "Limit #{pageSize} offset ${(pageNo - 1) * pageSize}")
    List<ClubCommentsVo> selectClubCommentsByClubId(@Param("clubId") int clubId,
                                                    @Param("pageNo") int pageNo,
                                                    @Param("pageSize") int pageSize);
}
