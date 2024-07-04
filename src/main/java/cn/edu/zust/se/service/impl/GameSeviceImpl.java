package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.GameMapper;
import cn.edu.zust.se.entity.GameType;
import cn.edu.zust.se.service.GameServiceI;
import cn.edu.zust.se.vo.GameTypeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@Service
@Transactional
public class GameSeviceImpl implements GameServiceI {
    @Resource
    GameMapper gameMapper;

    @Override
    public List<String> selectAllGameName() {
        return gameMapper.selectGameTypeNames();
    }

    @Override
    public void createGame(String name, String referrals, String introduction, Date insertTime) {
        gameMapper.insertGame(name,referrals,introduction,insertTime);
    }

    @Override
    public GameTypeVo getGameType(String name) {
        return gameMapper.selectGameTypeByName(name);
    }

    @Override
    public List<GameTypeVo> getAllGameType() {
        return gameMapper.selectGameType();
    }

    @Override
    public Integer getGameTypeNumber() {
        return gameMapper.selectGameTypeNumber();
    }

    @Override
    public Integer getGameTypeId(String tag) {
        return gameMapper.selectGameIdByName(tag);
    }
}
