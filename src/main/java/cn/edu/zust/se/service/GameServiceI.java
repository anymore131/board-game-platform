package cn.edu.zust.se.service;


import cn.edu.zust.se.entity.GameType;
import cn.edu.zust.se.vo.GameTypeVo;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
public interface GameServiceI {
    List<String> selectAllGameName();
    void createGame(String name,String referrals, String introduction,Date insertTime);
    GameTypeVo getGameType(String name);
    List<GameTypeVo> getAllGameType();
    Integer getGameTypeNumber();
    Integer getGameTypeId(String tag);
}
