package cn.edu.zust.se.dao;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.entity.Club;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserJoinVo;
import org.apache.ibatis.annotations.*;

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
                    @Param("createTime") Date createTime,
                    @Param("province") String province,
                    @Param("city") String city,
                    @Param("tags") String tags,
                    @Param("introduction") String introduction);

    /**
     * 通过俱乐部id寻找俱乐部
     * @param id    俱乐部id
     * @return      俱乐部
     */
    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user u " +
            "where c.status = 1 and c.id = ${id} and c.user_id = u.id")
    ClubVo getClubById(@Param("id") int id);
    @Select("select * from board_game_platform.t_club where id=#{id}")
    ClubBo getClubByIdB(@Param("id") int id);

    /**
     * 通过id寻找俱乐部的tags
     * @param id    俱乐部id
     * @return      tags
     */
    @Select("select tags " +
            "from board_game_platform.t_club " +
            "where id = #{id} and status = 1")
    String getClubTagsById(@Param("id") int id);

    /**
     * 通过俱乐部名称寻找俱乐部
     * @param clubName  俱乐部名称
     * @return          俱乐部
     */
    @Select("select * from board_game_platform.t_club where club_name = #{clubName}")
    Club getClubByName(@Param("clubName") String clubName);

    /**
     * 通过name，模糊搜索俱乐部的数量
     * @param clubName 模糊搜索值
     * @return 搜索到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_club " +
            "where club_name like concat('%',#{clubName},'%') and status = 1")
    int selectClubNumberByName(@Param("clubName") String clubName);

    /**
     * 通过name，模糊搜索俱乐部
     * @param clubName 模糊搜索值
     * @param pageNo   页码
     * @param pageSize 每页数量
     * @return 搜索到的俱乐部列表
     */
    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user u " +
            "where c.user_id = u.id and c.club_name like concat('%',#{clubName},'%') and c.status = 1 " +
            "order by (select count(j.id) from board_game_platform.t_club c,board_game_platform.t_user_join j " +
            "where c.id = j.club_id) DESC  " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ClubVo> selectClubByName(@Param("clubName") String clubName,
                                  @Param("pageNo") int pageNo,
                                  @Param("pageSize") int pageSize);

    /**
     * 通过用户id和俱乐部id找到该用户在该俱乐部的类型
     * @param userId    用户id
     * @param clubId    俱乐部id
     * @return          1为管理员；0为普通成员
     */
    @Select("select club_type " +
            "from board_game_platform.t_user_join " +
            "where user_id = #{userId} and club_id = #{clubId}")
    Integer selectClubTypeByUserIdAndClubId(@Param("userId") int userId,
                                            @Param("clubId") int clubId);

    /**
     * 通过tag，模糊搜索俱乐部的数量
     * @param tag 模糊搜索值，tag要用;包起来
     * @return 搜索到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_club " +
            "where t_club.tags like concat('%',#{tag},'%') and status = 1")
    int selectClubNumberByTag(@Param("tag") String tag);

    /**
     * 通过tag，模糊搜索俱乐部
     * @param tag      模糊搜索值，tag要用;包起来
     * @param pageNo   页码
     * @param pageSize 每页数量
     * @return 搜索到的俱乐部列表
     */
    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user u " +
            "where c.tags like concat('%',#{tag},'%') and c.status = 1 and c.user_id = u.id " +
            "order by (select count(j.id) from board_game_platform.t_club c,board_game_platform.t_user_join j " +
            "where c.id = j.club_id) DESC  " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ClubVo> selectClubByTag(@Param("tag") String tag,
                                 @Param("pageNo") int pageNo,
                                 @Param("pageSize") int pageSize);

    /**
     * 通过用户id返回用户参加俱乐部情况
     * @param userId    用户id
     * @return          俱乐部列表
     */
    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction,j.join_time " +
            "from board_game_platform.t_club c,board_game_platform.t_user u,board_game_platform.t_user_join j " +
            "where j.user_id = #{userId} and c.id = j.club_id and c.user_id = u.id and j.club_type = 0 and c.status = 1")
    List<ClubVo> selectClubVoByUserJoin(@Param("userId") int userId);

    /**
     * 通过俱乐部管理员id返回用户参加的俱乐部
     * @param userId    用户id
     * @return          俱乐部列表
     */
    @Select("select c.id,c.club_name,u.user_name,c.create_time,c.province,c.city,c.introduction,j.join_time " +
            "from board_game_platform.t_club c,board_game_platform.t_user u,board_game_platform.t_user_join j " +
            "where j.user_id = #{userId} and c.id = j.club_id and c.user_id = u.id and j.club_type = 1 and c.status = 1")
    List<ClubVo> selectClubVoManageByUserJoin(@Param("userId") int userId);

    /**
     * 通过娱乐部id返回俱乐部参加情况
     * @param clubId    俱乐部id
     * @return          俱乐部参加情况列表
     */
    @Select("select j.id,j.user_id,u.user_name,j.club_id,c.club_name,j.join_time,j.club_type " +
            "from board_game_platform.t_user_join j,board_game_platform.t_user u,board_game_platform.t_club c " +
            "where j.club_id = #{clubId} and j.club_id = c.id and j.user_id = u.id")
    List<UserJoinVo> selectUserJoinVo(@Param("clubId") int clubId);

    /**
     * 通过俱乐部id找参加俱乐部的人数
     * @param clubId    俱乐部id
     * @return          参加俱乐部的人数
     */
    @Select("select count(id) " +
            "from board_game_platform.t_user_join " +
            "where club_id = #{clubId}")
    Integer selectClubJoinCount(@Param("clubId") int clubId);

    /**
     * 添加用户参加俱乐部
     * @param userId    用户id
     * @param clubId    俱乐部id
     * @param joinTime  参加时间
     * @param clubType  用户在俱乐部的类型
     */
    @Insert("INSERT INTO board_game_platform.t_user_join " +
            "(user_id, club_id, join_time, club_type) " +
            "VALUES (#{userId}, #{clubId}, #{joinTime}, #{clubType})")
    void insertUserJoin(@Param("userId") int userId,
                        @Param("clubId") int clubId,
                        @Param("joinTime") Date joinTime,
                        @Param("clubType") int clubType);

    /**
     * 更新俱乐部数据
     * @param clubBo    俱乐部数据
     */
    @Update("UPDATE board_game_platform.t_club " +
            "SET club_name = #{clubName},province = #{province}," +
            "city = #{city},tags = #{tags},introduction = #{introduction} " +
            "WHERE id = #{id}")
    void updateClub(ClubBo clubBo);

    /**
     * 用户退出俱乐部
     * @param userId    用户id
     * @param clubId    俱乐部id
     */
    @Delete("delete from board_game_platform.t_user_join " +
            "where user_id = #{userId} and club_id = #{clubId}")
    void deleteUserJoin(@Param("userId")int userId,
                        @Param("clubId")int clubId);

    /**
     * 按地区搜索用户没有加入的俱乐部，按照俱乐部的人数递减
     * @param province  省
     * @param city      市
     * @return          俱乐部列表
     */
    @Select("select distinct c.id,c.club_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user_join u " +
            "where province = #{province} and city = #{city} and c.status = 1 and " +
            "c.id not in (select c.id from board_game_platform.t_club c,board_game_platform.t_user_join u " +
            "where c.id = u.club_id and u.user_id = #{userId}) " +
            "order by (select count(j.id) from board_game_platform.t_club c,board_game_platform.t_user_join j " +
            "where c.id = j.club_id) DESC " +
            "Limit 10 ")
    List<ClubVo> selectClubVoByProvinceAndCity(@Param("province")String province,
                                               @Param("city")String city,
                                               @Param("userId")int userId);

    @Select("select count(id) " +
            "from board_game_platform.t_user_join " +
            "where club_id = #{clubId}")
    Integer selectJoinCount(@Param("clubId") int clubId);

    @Select("select * from board_game_platform.t_club where status=#{visible}")
    List<ClubVo> selectClubVoByVisible(@Param("visible") int visible);

    @Update("UPDATE board_game_platform.t_club set status=1 where id=#{id}")
    void updateVisible(ClubBo club);

    @Select("select club_type " +
            "from board_game_platform.t_user_join " +
            "where club_id = #{clubId} and user_id = #{userId}")
    Integer selectUserClubType(@Param("userId")int userId,@Param("clubId")int clubId);
}
