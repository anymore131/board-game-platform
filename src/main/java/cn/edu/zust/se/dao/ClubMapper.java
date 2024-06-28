package cn.edu.zust.se.dao;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.vo.ClubVo;
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
            "(name, user_id, create_time, province, city, tags, introduction, status) " +
            "VALUES (#{name}, #{id}, #{createTime}, #{province}, #{city}, #{tags}, #{introduction}, 0)")
    void insertClub(@Param("name") String name,
                    @Param("userId") int id,
                    @Param("createTime")Date createTime,
                    @Param("province")String province,
                    @Param("city")String city,
                    @Param("tags")String tags,
                    @Param("introduction")String introduction);

    @Select("select c.id,c.name,u.user_name,c.create_time,c.province,c.city,c.introduction " +
            "from board_game_platform.t_club c,board_game_platform.t_user u " +
            "where c.status = 1 and c.id = ${id} and c.user_id = u.id")
    ClubVo getClubById(@Param("id") int id);

    @Select("select tags " +
            "from board_game_platform.t_club " +
            "where id = ${id} and status = 1")
    String getClubTagsById(@Param("id") int id);

    /**
     * 通过name，模糊搜索俱乐部的数量
     * @param name  模糊搜索值
     * @return      搜索到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_club " +
            "where name like #{%name%} and status = 1")
    int selectClubNumberByName(@Param("name")String name);

    /**
     * 通过name，模糊搜索俱乐部
     * @param name      模糊搜索值
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @return          搜索到的俱乐部列表
     */
    @Select("select * " +
            "from board_game_platform.t_club " +
            "where name like #{%name%} and status = 1 " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ClubBo> selectClubByName(@Param("name") String name,
                                  @Param("pageNo")int pageNo,
                                  @Param("pageSize")int pageSize);

    /**
     * 通过tag，模糊搜索俱乐部的数量
     * @param tag   模糊搜索值，tag要用;包起来
     * @return      搜索到的数量
     */
    @Select("SELECT COUNT(id) " +
            "from board_game_platform.t_club " +
            "where name like #{%tag%} and status = 1")
    int selectClubNumberByTag(@Param("tag")int tag);

    /**
     * 通过tag，模糊搜索俱乐部
     * @param tag       模糊搜索值，tag要用;包起来
     * @param pageNo    页码
     * @param pageSize  每页数量
     * @return          搜索到的俱乐部列表
     */
    @Select("select * " +
            "from board_game_platform.t_club c " +
            "where c.tags like #{%tag%} and c.status = 1 " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<ClubBo> selectClubByTag(@Param("tag")int tag,
                                 @Param("pageNo")int pageNo,
                                 @Param("pageSize")int pageSize);


}
