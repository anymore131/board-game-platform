package cn.edu.zust.se.dao;

import cn.edu.zust.se.entity.Club;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserJoinVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
public interface ClubMapper {
    /**
     * 新建俱乐部
     */
    @Insert("INSERT INTO board_game_platform.t_club " +
            "(club_name, user_id, create_time, province, city, tags, introduction, status) " +
            "VALUES (#{clubName}, #{userId}, #{createTime}, #{province}, #{city}, #{tags}, #{introduction}, 0)")
    void insertClub(@Param("clubName") String clubName,
                    @Param("userId") int userId,
                    @Param("createTime")Date createTime,
                    @Param("province")String province,
                    @Param("city")String city,
                    @Param("tags")String tags,
                    @Param("introduction")String introduction);

    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user u " +
            "where c.status = 1 and c.id = ${id} and c.user_id = u.id")
    ClubVo getClubById(@Param("id") int id);

    @Select("select tags " +
            "from board_game_platform.t_club " +
            "where id = ${id} and status = 1")
    String getClubTagsById(@Param("id") int id);

    @Select("select * from board_game_platform.t_club where club_name = #{clubName}")
    Club getClubByName(@Param("clubName") String clubName);

    /**
     * 通过name，模糊搜索俱乐部的数量
     * @param clubName  模糊搜索值
     * @return      搜索到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_club " +
            "where club_name like concat('%',#{clubName},'%') and status = 1")
    int selectClubNumberByName(@Param("clubName")String clubName);

    /**
     * 通过name，模糊搜索俱乐部
     * @param clubName      模糊搜索值
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @return          搜索到的俱乐部列表
     */
    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user u " +
            "where c.user_id = u.id and c.club_name like concat('%',#{clubName},'%') and c.status = 1 " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ClubVo> selectClubByName(@Param("clubName") String clubName,
                                  @Param("pageNo")int pageNo,
                                  @Param("pageSize")int pageSize);

    @Select("select club_type " +
            "from board_game_platform.t_user_join " +
            "where user_id = #{userId} and club_id = #{clubId}")
    Integer selectClubTypeByUserIdAndClubId(@Param("userId") int userId,
                                            @Param("clubId") int clubId);

    /**
     * 通过tag，模糊搜索俱乐部的数量
     * @param tag   模糊搜索值，tag要用;包起来
     * @return      搜索到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_club " +
            "where t_club.tags like concat('%',#{tag},'%') and status = 1")
    int selectClubNumberByTag(@Param("tag")String tag);

    /**
     * 通过tag，模糊搜索俱乐部
     * @param tag       模糊搜索值，tag要用;包起来
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @return          搜索到的俱乐部列表
     */
    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user u " +
            "where c.tags like concat('%',#{tag},'%') and c.status = 1 and c.user_id = u.id " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ClubVo> selectClubByTag(@Param("tag")String tag,
                                 @Param("pageNo")int pageNo,
                                 @Param("pageSize")int pageSize);

    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction,j.join_time " +
            "from board_game_platform.t_club c,board_game_platform.t_user u,board_game_platform.t_user_join j " +
            "where j.user_id = #{userId} and c.id = j.club_id and c.user_id = u.id and j.club_type = 0 and c.status = 1")
    List<ClubVo> selectClubVoByUserJoin(@Param("userId")int userId);

    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction,j.join_time " +
            "from board_game_platform.t_club c,board_game_platform.t_user u,board_game_platform.t_user_join j " +
            "where j.user_id = #{userId} and c.id = j.club_id and c.user_id = u.id and j.club_type = 1 and c.status = 1")
    List<ClubVo> selectClubVoManageByUserJoin(@Param("userId") int userId);

    @Select("select j.id,j.user_id,u.user_name,j.club_id,c.club_name,j.join_time,j.club_type " +
            "from board_game_platform.t_user_join j,board_game_platform.t_user u,board_game_platform.t_club c " +
            "where j.club_id = #{clubId} and j.club_id = c.id and j.user_id = u.id")
    List<UserJoinVo> selectUserJoinVo(@Param("clubId") int clubId);

    @Select("select count(id) " +
            "from board_game_platform.t_user_join " +
            "where club_id = #{clubId}")
    Integer selectClubJoinCount(@Param("clubId") int clubId);

    @Insert("INSERT INTO board_game_platform.t_user_join " +
            "(user_id, club_id, join_time, club_type) " +
            "VALUES (#{userId}, #{clubId}, #{joinTime}, #{clubType})")
    void insertUserJoin(@Param("userId") int userId,
                        @Param("clubId") int clubId,
                        @Param("joinTime") Date joinTime,
                        @Param("clubType")int clubType);
}
