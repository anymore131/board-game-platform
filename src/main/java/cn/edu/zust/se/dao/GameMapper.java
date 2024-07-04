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
            "(name, introduction, insert_time) " +
            "VALUES (#{name},  #{introduction}, #{insertTime})")
    void insertGame(@Param("name") String name,
                    @Param("introduction") String introduction,
                    @Param("insertTime") Date insertTime);

    @Select("SELECT name " +
            "FROM board_game_platform.t_game_type " +
            "WHERE name = #{name}")
    String selectGameByName(@Param("name") String name);
    /**
     * 通过id获取游戏信息
     * @param id    游戏id
     * @return      游戏信息
     */
    @Select("select g.id,g.name,g.referrals,g.introduction,g.insert_time " +
            "from board_game_platform.t_game_type g " +
            "where g.id = #{id} ")
    GameTypeVo selectGameById(@Param("id") int id);

    @Select("select * from board_game_platform.t_game_type")
    List<GameTypeVo> selectGameType();

    @Select("select * from board_game_platform.t_game_type where name = #{name}")
    GameTypeVo selectGameTypeByName(String name);

    @Select("select COUNT(id) from board_game_platform.t_game_type")
    int selectGameTypeNumber();

    /**
     * 返回所有游戏名
     * @return  游戏名列表
     */
    @Select("select name from board_game_platform.t_game_type")
    List<String> selectGameTypeNames();

    /**
     * 通过标签名返回标签id
     * @param name  标签名
     * @return      标签id
     */
    @Select("select id from board_game_platform.t_game_type where name = #{name}")
    Integer selectGameIdByName(String name);
}
