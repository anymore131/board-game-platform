package cn.edu.zust.se.dao;

import cn.edu.zust.se.vo.ActivityPictureVo;
import cn.edu.zust.se.vo.ClubPictureVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lenovo
 */
public interface PictureMapper {
    @Select("select p.id,p.name,p.fname,p.activity_id,a.activity_name " +
            "from board_game_platform.t_activity_picture p,board_game_platform.t_activity a " +
            "where p.activity_id = #{activityId} and a.id = p.activity_id ")
    List<ActivityPictureVo> selectActivityPictureVoByActivityId(@Param("activityId") int activityId);

    @Select("select p.id,p.name,p.fname,p.club_id,a.activity_name " +
            "from board_game_platform.t_club_picture p,board_game_platform.t_activity a " +
            "where p.club_id = #{clubId} and a.id = p.club_id ")
    List<ClubPictureVo> selectClubPictureVoByClubId(@Param("clubId") int clubId);

    @Insert("INSERT INTO board_game_platform.t_activity_picture " +
            "(name, fname, activity_id) " +
            "VALUES (#{name}, #{fname}, #{activityId})")
    void insertActivityPicture(@Param("name") String name,
                               @Param("fname") String fname,
                               @Param("activityId") int activityId);

    @Insert("INSERT INTO board_game_platform.t_club_picture " +
            "(name, fname, club_id) " +
            "VALUES (#{name}, #{fname}, #{clubId})")
    void insertClubPicture(@Param("name") String name,
                           @Param("fname") String fname,
                           @Param("clubId") int clubId);

    @Select("select count(id) " +
            "from board_game_platform.t_club_picture " +
            "where club_id=#{clubId}")
    Integer selectClubPictureCountByClubId(@Param("clubId") int clubId);

    @Select("select count(id) " +
            "from board_game_platform.t_activity_picture " +
            "where activity_id=#{activityId}")
    Integer selectActivityPictureCountByActivityId(@Param("activityId") int activityId);
}
