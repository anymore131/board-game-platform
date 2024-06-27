package cn.edu.zust.se.dao;

import cn.edu.zust.se.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;

/**
 * @author Lenovo
 */

public interface UserMapper {
    @Select("select * " +
            "from board_game_platform.t_user " +
            "where user_name = #{userName} and password = #{password}")
    UserVo selectUserByUserNameAndPassword(@Param("userName") String userName,
                                           @Param("password") String password);

    @Select("select * from board_game_platform.t_user where user_name = #{userName}")
    UserVo selectUserByUserName(@Param("userName") String userName);

    @Insert("INSERT INTO board_game_platform.t_user " +
            "(name, user_name, password, age, gender, introduction, " +
            "type, email, mobile, province, city, register, QQ, weixin, avatar_fname, status) " +
            "VALUES (#{userName}, #{password}, #{password}, null, null, null, " +
            "0, null, null, null, null, #{registerTime}, null, null, #{avatarFname}, 1)")
    void insertUser(@Param("userName")String userName,
                      @Param("password")String password,
                      @Param("name")String name,
                      @Param("registerTime") Date registerTime,
                      @Param("avatarFname")String avatarFname);
}
