package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.bo.UserBo;
import cn.edu.zust.se.dao.ClubMapper;
import cn.edu.zust.se.dao.GameMapper;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.vo.ClubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lenovo
 */
@Service
@Transactional
public class ClubServiceImpl implements ClubServiceI {
    @Resource
    ClubMapper clubMapper;
    @Resource
    GameMapper gameMapper;

    @Override
    public ClubVo getClubVo(int id) {
        ClubVo clubVo = clubMapper.getClubById(id);
        String s = clubMapper.getClubTagsById(id);
        String[] ss = s.split(";");
        List<String> tags = new ArrayList<String>();
        for (String tag : ss) {
            tag = gameMapper.selectGameById(Integer.parseInt(tag)).getName();
            tags.add(tag);
        }
        clubVo.setTags(tags);
        return clubVo;
    }
}
