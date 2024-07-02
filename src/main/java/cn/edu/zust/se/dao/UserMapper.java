package cn.edu.zust.se.dao;

import cn.edu.zust.se.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */

public interface UserMapper {
    @Select("select * " +
            "from board_game_platform.t_user " +
            "where user_name = #{userName} and password = #{password}")
    UserVo selectUserByUserNameAndPassword(@Param("userName") String userName,
                                           @Param("password") String password);

    @Select("select * from board_game_platform.t_user where id=#{id}")
    UserVo selectUserById(@Param("id") int id);

    @Select("select * from board_game_platform.t_user where user_name = #{userName}")
    UserVo selectUserByUserName(@Param("userName") String userName);

    @Insert("INSERT INTO board_game_platform.t_user " +
            "(name, user_name, password, age, gender, introduction, " +
            "type, email, mobile, province, city, register, QQ, weixin, avatar_fname, status) " +
            "VALUES (#{userName}, #{password}, #{password}, null, null, null, " +
            "0, null, null, null, null, #{registerTime}, null, null, #{avatarFname}, 1)")
    void insertUser(@Param("userName") String userName,
                    @Param("password") String password,
                    @Param("name") String name,
                    @Param("registerTime") Date registerTime,
                    @Param("avatarFname") String avatarFname);

    @Select("select club_id " +
            "from board_game_platform.t_user_join " +
            "where user_id = #{userId}")
    List<Integer> selectUserJoinByUserId(@Param("userId") int userId);

    @Update("UPDATE board_game_platform.t_user t " +
            "SET t.avatar_fname = #{fname} " +
            "WHERE t.id = #{id}")
    void updateUserAvatar(@Param("id") int id, @Param("fname") String fname);

    @Select("SELECT COUNT(id) " +
            "FROM board_game_platform.t_user " +
            "WHERE user_name LIKE CONCAT('%',#{userName},'%')")
    Integer getUserByUserName(@Param("userName") String userName);

    @Select("SELECT * " +
            "FROM board_game_platform.t_user " +
            "WHERE user_name LIKE CONCAT('%',#{userName},'%') " +
            "LIMIT #{pageSize} OFFSET ${(pageNo - 1) * pageSize}")
    List<UserVo> getUserVoByUserName(@Param("userName") String userName,
                                     @Param("pageNo") int pageNo,
                                   @Param("pageSize") int pageSize);
}
