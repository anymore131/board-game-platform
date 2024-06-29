package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.GameMapper;
import cn.edu.zust.se.service.GameServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class GameSeviceImpl implements GameServiceI {
    @Resource
    GameMapper gameMapper;

    @Override
    public List<String> selectAllGameName() {
        return gameMapper.selectGameTypeNames();
    }
}
