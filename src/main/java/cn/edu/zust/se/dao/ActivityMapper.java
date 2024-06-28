package cn.edu.zust.se.dao;

import cn.edu.zust.se.entity.Activity;
import cn.edu.zust.se.vo.ActivityVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
public interface ActivityMapper {
    @Insert("INSERT INTO board_game_platform.t_activity " +
            "(club_id, name, introduction, tags, address, start_time, end_time, create_time) " +
            "VALUES (1, '1', null, '1', '1', '2024-06-28 22:46:10', '2024-06-29 22:46:16', '2024-06-14')")
    void insertActivity(@Param("clubId")int clubId,
                        @Param("name")String name,
                        @Param("introduction")String introduction,
                        @Param("tags")String tags,
                        @Param("address") String address,
                        @Param("startTime")Date startTime,
                        @Param("endTime")Date endTime,
                        @Param("createTime")Date createTime);

    @Select("SELECT a.id,c.name,a.name,a.introduction,a.address,a.start_time,a.end_time,a.create_time " +
            "from board_game_platform.t_activity a,board_game_platform.t_club c " +
            "where a.club_id = #{clubId} and c.id = a.club_id and c.status = 1")
    List<ActivityVo> selectActivityVoByClubId(@Param("clubId")int clubId);

    @Select("SELECT a.tags " +
            "from board_game_platform.t_activity a " +
            "where a.id = #{id} ")
    String selectActivityVoTagsById(@Param("id")int id);

    @Select("select COUNT(id) " +
            "from board_game_platform.t_user_attend " +
            "where activity_id = #{id}")
    int selectActivityVoNumberById(@Param("id")int id);

    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_activity " +
            "where club_id = #{clubId}  and date(start_time) between #{nowTime} and '2100-01-01 00:00:00'")
    int selectActivityNumberByClubId(@Param("clubId")int clubId,
                                     @Param("nowTime")Date nowTime);

    @Select("SELECT * " +
            "from board_game_platform.t_activity " +
            "where club_id = #{clubId}  and date(start_time) between #{nowTime} and '2100-01-01 00:00:00' " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ActivityVo> selectActivityByClubId(@Param("clubId")int clubId,
                                            @Param("pageNo")int pageNo,
                                            @Param("pageSize")int pageSize,
                                            @Param("nowTime")Date nowTime);

    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_activity " +
            "where name like #{%name%}  and date(start_time) between #{nowTime} and '2100-01-01 00:00:00' ")
    int selectActivityNumberByName(@Param("name")String name,
                                   @Param("nowTime")Date nowTime);

    @Select("SELECT * " +
            "from board_game_platform.t_activity " +
            "where name like #{%name%} and date(start_time) between #{nowTime} and '2100-01-01 00:00:00' " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ActivityVo> selectActivityByName(@Param("name")String name,
                                          @Param("pageNo")int pageNo,
                                          @Param("pageSize")int pageSize,
                                          @Param("nowTime")Date nowTime);

    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_activity " +
            "where tags like #{%tag%}  and date(start_time) between #{nowTime} and '2100-01-01 00:00:00' ")
    int selectActivityNumberByTag(@Param("tag")int tag,
                                  @Param("nowTime")Date nowTime);

    @Select("select * " +
            "from board_game_platform.t_activity a " +
            "where a.tags like #{%tag%} and date(a.start_time) between #{nowTime} and '2100-01-01 00:00:00' " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ActivityVo> selectActivityByTag(@Param("tag")int tag,
                                         @Param("pageNo")int pageNo,
                                         @Param("pageSize")int pageSize,
                                         @Param("nowTime")Date nowTime);

}
