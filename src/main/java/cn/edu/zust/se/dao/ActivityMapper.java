package cn.edu.zust.se.dao;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.vo.ActivityVo;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
public interface ActivityMapper {
    /**
     * 通过俱乐部id和活动名找活动
     * @param clubId        俱乐部id
     * @param activityName  活动名
     * @return              活动id
     */
    @Select("select id " +
            "from board_game_platform.t_activity " +
            "where club_id = #{clubId} and activity_name = #{activityName}")
    Integer selectActivityIdByClubIdAndActivityName(@Param("clubId") int clubId, @Param("activityName") String activityName);

    @Select("SELECT a.id,a.club_id,c.club_name,a.activity_name,a.introduction,a.address,a.start_time,a.end_time,a.create_time " +
            "FROM board_game_platform.t_activity a,board_game_platform.t_club c " +
            "WHERE a.club_id = #{clubId} and c.id = a.club_id ")
    List<ActivityVo> listClubActivity(@Param("clubId") int clubId);

    /**
     * 添加活动
     * @param activityBo    活动
     */
    @Insert("INSERT INTO board_game_platform.t_activity " +
            "(club_id, activity_name, introduction, tags, address, start_time, end_time, create_time) " +
            "VALUES (#{clubId}, #{activityName}, #{introduction}, #{tags}, #{address}, #{startTime}, #{endTime}, #{createTime})")
    void insertActivity(ActivityBo activityBo);

    /**
     * 修改活动
     * @Param activityBo
     */
    @Update("UPDATE board_game_platform.t_activity " +
            "SET activity_name = #{activityName},introduction = #{introduction}," +
            "tags = #{tags}, address = #{address},start_time = #{startTime},end_time = #{endTime} " +
            "WHERE id = #{id}")
    void updateActivity(ActivityBo activityBo);
    @Delete("DELETE " +
            "FROM board_game_platform.t_activity " +
            "WHERE id = #{id}")
    void deleteActivityById(@Param("id") int id);

    @Delete("DELETE " +
            "FROM board_game_platform.t_activity_comments " +
            "WHERE activity_id = #{activityId}")
    void deleteActivityComments(@Param("activityId") int activityId);

    @Delete("DELETE " +
            "FROM board_game_platform.t_user_attend " +
            "WHERE activity_id = #{activityId}")
    void deleteActivity(@Param("activityId") int activityId);

    /**
     * 用过活动id寻找活动
     * @param id    活动id
     * @return      活动Vo
     */
    @Select("select a.id,a.club_id,c.club_name,a.activity_name,a.introduction,a.address,a.start_time,a.end_time,a.create_time " +
            "from board_game_platform.t_activity a,board_game_platform.t_club c " +
            "where a.id = #{id} and c.id = a.club_id ")
    ActivityVo selectActivityById(@Param("id") int id);

    /**
     * 通过俱乐部id找寻所有活动
     * @param clubId    俱乐部id
     * @return
     */
    @Select("SELECT a.id,a.club_id,c.club_name,a.activity_name,a.introduction,a.address,a.start_time,a.end_time,a.create_time " +
            "from board_game_platform.t_activity a,board_game_platform.t_club c " +
            "where a.club_id = #{clubId} and c.id = a.club_id and c.status = 1")
    List<ActivityVo> selectActivityVoByClubId(@Param("clubId") int clubId);

    /**
     * 通过id返回活动的tags
     * @param id    活动id
     * @return      tags
     */
    @Select("SELECT a.tags " +
            "from board_game_platform.t_activity a " +
            "where a.id = #{id} ")
    String selectActivityVoTagsById(@Param("id") int id);

    /**
     * 通过id返回参加活动的人数
     * @param id    活动id
     * @return      返回人数
     */
    @Select("select COUNT(id) " +
            "from board_game_platform.t_user_attend " +
            "where activity_id = #{id}")
    Integer selectActivityVoNumberById(@Param("id") int id);

    /**
     * 通过俱乐部id找活动，只找当前时间还没开始的活动，返回数量
     * @param clubId    俱乐部id
     * @param nowTime   当前时间
     * @return          返回活动数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_activity " +
            "where club_id = #{clubId}  and date(start_time) between #{nowTime} and '2100-01-01 00:00:00'")
    Integer selectActivityNumberByClubId(@Param("clubId") int clubId,
                                         @Param("nowTime") Date nowTime);

    /**
     * 通过俱乐部id找活动，只找当前世界按还没开始的活动，按页分割
     * @param clubId    俱乐部ID
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @param nowTime   当前时间
     * @return          返回活动列表
     */
    @Select("SELECT a.id,a.club_id,c.club_name,a.activity_name,a.introduction,a.address,a.start_time,a.end_time,a.create_time " +
            "from board_game_platform.t_activity a,board_game_platform.t_club c " +
            "where a.club_id = #{clubId} and c.id = #{clubId} and date(a.start_time) between #{nowTime} and '2100-01-01 00:00:00' " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ActivityVo> selectActivityByClubId(@Param("clubId") int clubId,
                                            @Param("pageNo") int pageNo,
                                            @Param("pageSize") int pageSize,
                                            @Param("nowTime") Date nowTime);

    /**
     * 通过活动名找活动，只找当前时间还没开始的活动，返回找到的数量
     * @param activityName  活动名
     * @param nowTime       当前时间
     * @return              返回找到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_activity " +
            "where activity_name like concat('%',#{activityName},'%') and date(start_time) between #{nowTime} and '2100-01-01 00:00:00' ")
    Integer selectActivityNumberByName(@Param("activityName") String activityName,
                                       @Param("nowTime") Date nowTime);

    /**
     * 通过活动名找活动，只找当前时间还没开始的活动，并且按页分割
     * @param activityName  活动名
     * @param pageNo        页码
     * @param pageSize      每页数量
     * @param nowTime       当前时间
     * @return              返回活动列表
     */
    @Select("SELECT a.id,a.club_id,c.club_name,a.activity_name,a.introduction,a.address,a.start_time,a.end_time,a.create_time " +
            "from board_game_platform.t_activity a,board_game_platform.t_club c " +
            "where activity_name like concat('%',#{activityName},'%') and c.id = a.club_id and date(start_time) between #{nowTime} and '2100-01-01 00:00:00' " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ActivityVo> selectActivityByName(@Param("activityName") String activityName,
                                          @Param("pageNo") int pageNo,
                                          @Param("pageSize") int pageSize,
                                          @Param("nowTime") Date nowTime);

    /**
     * 通过标签寻找活动，只找当前时间还没开始的活动，返回找到的数量
     * @param tag       游戏标签
     * @param nowTime   当前时间
     * @return          返回找到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_activity " +
            "where tags like concat('%',#{tag},'%')  and date(start_time) between #{nowTime} and '2100-01-01 00:00:00' ")
    Integer selectActivityNumberByTag(@Param("tag") String tag,
                                      @Param("nowTime") Date nowTime);

    /**
     * 通过标签寻找活动，只找当前时间还没开始的活动，并且按页分割
     * @param tag       游戏标签
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @param nowTime   当前时间
     * @return          返回活动列表
     */
    @Select("select a.id,a.club_id,c.club_name,a.activity_name,a.introduction,a.address,a.start_time,a.end_time,a.create_time " +
            "from board_game_platform.t_activity a,board_game_platform.t_club c " +
            "where a.tags like concat('%',#{tag},'%') and c.id = a.club_id and date(a.start_time) between #{nowTime} and '2100-01-01 00:00:00' " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ActivityVo> selectActivityByTag(@Param("tag") String tag,
                                         @Param("pageNo") int pageNo,
                                         @Param("pageSize") int pageSize,
                                         @Param("nowTime") Date nowTime);

    /**
     * 通过用户id和活动id找到用户是否参加活动，没有返回null
     * @param userId        用户id
     * @param activityId    活动id
     * @return              没有返回null
     */
    @Select("select * " +
            "from board_game_platform.t_user_attend " +
            "where user_id = #{userId} and activity_id = #{activityId}")
    Integer selectActivityByUserIdAndActivityId(@Param("userId") int userId,
                                                @Param("activityId") int activityId);

    /**
     * 添加用户参加活动
     * @param userId        用户id
     * @param activityId    活动id
     */
    @Insert("INSERT INTO board_game_platform.t_user_attend " +
            "(user_id, activity_id) " +
            "VALUES (#{userId}, #{activityId})")
    void insertUserAttend(@Param("userId") int userId,
                          @Param("activityId") int activityId);

    /**
     * 用户取消参加活动
     * @param userId        用户id
     * @param activityId    活动id
     */
    @Delete("delete from board_game_platform.t_user_attend " +
            "where user_id = #{userId} and activity_id = #{activityId}")
    void deleteUserAttend(@Param("userId") int userId,
                          @Param("activityId") int activityId);
}
