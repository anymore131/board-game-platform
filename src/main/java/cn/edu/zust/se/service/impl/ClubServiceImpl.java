package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.ClubMapper;
import cn.edu.zust.se.dao.GameMapper;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.vo.ClubVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
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
        clubVo.setTags(splitTag(s.split(";")));
        return clubVo;
    }

    @Override
    public List<ClubVo> getClubVoByUserJoin(int userId) {
        List<ClubVo> clubVos = clubMapper.selectClubVoByUserJoin(userId);
        for (ClubVo clubVo : clubVos) {
            String s = clubMapper.getClubTagsById(clubVo.getId());
            clubVo.setTags(splitTag(s.split(";")));
            clubVo.setJoined(1);
        }
        return clubVos;
    }

    @Override
    public List<ClubVo> getClubVoManageByUserJoin(int userId) {
        List<ClubVo> clubVos = clubMapper.selectClubVoManageByUserJoin(userId);
        for (ClubVo clubVo : clubVos) {
            String s = clubMapper.getClubTagsById(clubVo.getId());
            clubVo.setTags(splitTag(s.split(";")));
            clubVo.setJoined(1);
        }
        return clubVos;
    }

    @Override
    public Integer getClubNumberByName(String clubName) {
        return clubMapper.selectClubNumberByName(clubName);
    }

    @Override
    public List<ClubVo> getClubVoByName(String clubName, int pageNo, int pageSize,int userId) {
        List<ClubVo> clubVos = clubMapper.selectClubByName(clubName, pageNo, pageSize);
        for (ClubVo clubVo : clubVos) {
            String s = clubMapper.getClubTagsById(clubVo.getId());
            clubVo.setTags(splitTag(s.split(";")));
            if(clubMapper.selectClubTypeByUserIdAndClubId(userId,clubVo.getId()) == 1
                    ||clubMapper.selectClubTypeByUserIdAndClubId(userId,clubVo.getId()) == 0){
                clubVo.setJoined(1);
            }else {
                clubVo.setJoined(0);
            }
        }
        return clubVos;
    }

    @Override
    public Integer getClubNumberByTag(String tag) {
        return clubMapper.selectClubNumberByTag(';' + tag + ';');
    }

    @Override
    public List<ClubVo> getClubVoByTag(String tag, int pageNo, int pageSize,int userId) {
        List<ClubVo> clubVos = clubMapper.selectClubByTag(tag, pageNo, pageSize);
        for (ClubVo clubVo : clubVos) {
            String s = clubMapper.getClubTagsById(clubVo.getId());
            clubVo.setTags(splitTag(s.split(";")));
            if(clubMapper.selectClubTypeByUserIdAndClubId(userId,clubVo.getId()) == 1
                    ||clubMapper.selectClubTypeByUserIdAndClubId(userId,clubVo.getId()) == 0){
                clubVo.setJoined(1);
            }else {
                clubVo.setJoined(0);
            }
        }
        return clubVos;
    }

    @Override
    public boolean insetClub(ClubVo clubVo) {
        if(clubMapper.getClubByName(clubVo.getClubName()) != null){
            StringBuilder s = new StringBuilder(";");
            List<String> tags = clubVo.getTags();
            for(String tag : tags){
                s.append(tag).append(";");
            }
            clubMapper.insertClub(clubVo.getClubName(),clubVo.getUserId(),
                    new Date(new java.util.Date().getTime()),clubVo.getProvince(),
                    clubVo.getCity(), String.valueOf(s), clubVo.getIntroduction());
            return true;
        }
        return false;
    }

    public List<String> splitTag(String[] ss){
        List<String> tags = new ArrayList<String>();
        for (String tag : ss) {
            if (tag != null && !tag.equals("")) {
                tag = gameMapper.selectGameById(Integer.parseInt(tag)).getName();
                tags.add(tag);
            }
        }
        return tags;
    }
}
