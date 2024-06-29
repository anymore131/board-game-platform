package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.ActivityMapper;
import cn.edu.zust.se.dao.GameMapper;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.vo.ActivityVo;
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
public class ActivityServiceImpl implements ActivityServiceI {
    @Resource
    ActivityMapper activityMapper;
    @Resource
    GameMapper gameMapper;

    @Override
    public List<ActivityVo> getActivityVoById(int id,int userId) {
        List<ActivityVo> activityVos = activityMapper.selectActivityVoByClubId(id);
        for (ActivityVo activityVo : activityVos) {
            String s = activityMapper.selectActivityVoTagsById(activityVo.getId());
            activityVo.setNumber(activityMapper.selectActivityVoNumberById(activityVo.getId()));
            activityVo.setTags(splitTag(s.split(";")));
            if (activityMapper.selectActivityByUserIdAndActivityId(userId,activityVo.getId())!=null){
                activityVo.setAttended(1);
            }else {
                activityVo.setAttended(0);
            }
        }
        return activityVos;
    }

    @Override
    public int getActivityVoNumberByTag(String tag) {
        Date nowTime = new Date(new java.util.Date().getTime());
        return activityMapper.selectActivityNumberByTag(';' + tag + ';',nowTime);
    }

    @Override
    public List<ActivityVo> getActivityVoByTag(String tag, int pageNo, int pageSize, int userId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activityVos = activityMapper.selectActivityByTag(';'+tag+';',pageNo,pageSize,nowTime);
        for (ActivityVo activityVo : activityVos) {
            String s = activityMapper.selectActivityVoTagsById(activityVo.getId());
            activityVo.setNumber(activityMapper.selectActivityVoNumberById(activityVo.getId()));
            activityVo.setTags(splitTag(s.split(";")));
            if (activityMapper.selectActivityByUserIdAndActivityId(userId,activityVo.getId())!=null){
                activityVo.setAttended(1);
            }else {
                activityVo.setAttended(0);
            }
        }
        return activityVos;
    }

    @Override
    public int getActivityVoNumberByName(String name) {
        Date nowTime = new Date(new java.util.Date().getTime());
        return activityMapper.selectActivityNumberByName(name,nowTime);
    }

    @Override
    public List<ActivityVo> getActivityVoByName(String name, int pageNo, int pageSize, int userId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activityVos = activityMapper.selectActivityByName(name,pageNo,pageSize,nowTime);
        for (ActivityVo activityVo : activityVos) {
            String s = activityMapper.selectActivityVoTagsById(activityVo.getId());
            activityVo.setNumber(activityMapper.selectActivityVoNumberById(activityVo.getId()));
            activityVo.setTags(splitTag(s.split(";")));
            if (activityMapper.selectActivityByUserIdAndActivityId(userId,activityVo.getId())!=null){
                activityVo.setAttended(1);
            }else {
                activityVo.setAttended(0);
            }
        }
        return activityVos;
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
