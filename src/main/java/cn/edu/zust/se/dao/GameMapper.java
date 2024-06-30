package cn.edu.zust.se.dao;

import cn.edu.zust.se.entity.GameType;
import cn.edu.zust.se.vo.GameTypeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
public interface GameMapper {
    @Insert("INSERT INTO board_game_platform.t_game_type " +
            "(name, referrals, introduction, insert_time, user_id, type) " +
            "VALUES (#{name}, #{referrals}, #{introduction}, #{insertTime}, #{userId}, 0)")
    void insertGame(@Param("name") String name,
                    @Param("referrals") String referrals,
                    @Param("introduction") String introduction,
                    @Param("insertTime") Date insertTime,
                    @Param("userId") int userId);

    @Select("select g.id,g.name,g.referrals,g.introduction,g.insert_time,u.user_name " +
            "from board_game_platform.t_game_type g,board_game_platform.t_user u " +
            "where g.id = #{id} and g.user_id = u.id and g.type = 1")
    GameTypeVo selectGameById(@Param("id") int id);

    @Select("select * from board_game_platform.t_game_type")
    List<GameType> selectGameType();

    @Select("select * from board_game_platform.t_game_type where name = #{name}")
    GameType selectGameTypeByName(String name);

    @Select("select COUNT(id) from board_game_platform.t_game_type")
    int selectGameTypeNumber();

    @Select("select name from board_game_platform.t_game_type")
    List<String> selectGameTypeNames();
}
