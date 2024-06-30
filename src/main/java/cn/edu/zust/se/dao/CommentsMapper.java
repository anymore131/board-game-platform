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
    @Insert("INSERT INTO board_game_platform.t_activity_comments " +
            "(user_id, activity_id, comments, comments_time) " +
            "VALUES (#{userId}, #{activityId}, #{comments}, #{commentsTime})")
    void insertActivityComments(@Param("userId") int userId,
                                @Param("activityId") int activityId,
                                @Param("comments") String comments,
                                @Param("commentsTime") Date commentsTime);

    @Insert("INSERT INTO board_game_platform.t_club_comments " +
            "(user_id, club_id, comments, comments_time) " +
            "VALUES (#{userId}, #{clubId}, #{comments}, #{commentsTime})")
    void insertClubComments(@Param("userId") int userId,
                            @Param("clubId") int clubId,
                            @Param("comments") String comments,
                            @Param("commentsTime") Date commentsTime);

    @Select("select count(*) " +
            "from board_game_platform.t_activity_comments " +
            "where activity_id = #{activityId}")
    int selectActivityCommentsByActivityIdNumber(@Param("activityId") int activityId);

    @Select("select c.id,club_id,c.activity_id,u.user_name,a.activity_name,c.comments,c.comments_time " +
            "from board_game_platform.t_activity_comments c,board_game_platform.t_user u ,board_game_platform.t_activity a " +
            "where c.activity_id = #{activityId} and c.user_id = u.id and c.activity_id = a.id " +
            "Limit #{pageSize} offset ${(pageNo - 1) * pageSize}")
    List<ActivityCommentsVo> selectActivityCommentsByActivityId(@Param("activityId") int activityId,
                                                                @Param("pageNo") int pageNo,
                                                                @Param("pageSize") int pageSize);

    @Select("select count(*) " +
            "from board_game_platform.t_club_comments " +
            "where club_id = #{clubId}")
    int selectClubCommentsByClubIdNumber(@Param("clubId") int clubId);

    @Select("select c.id,c.user_id,c.club_id,u.user_name,cl.club_name,c.comments,c.comments_time " +
            "from board_game_platform.t_club_comments c,board_game_platform.t_user u ,board_game_platform.t_club cl " +
            "where c.club_id = #{clubId} and c.user_id = u.id and c.club_id = cl.id " +
            "Limit #{pageSize} offset ${(pageNo - 1) * pageSize}")
    List<ClubCommentsVo> selectClubCommentsByClubId(@Param("clubId") int clubId,
                                                    @Param("pageNo") int pageNo,
                                                    @Param("pageSize") int pageSize);

}
