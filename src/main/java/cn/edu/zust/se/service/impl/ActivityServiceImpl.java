package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.ActivityMapper;
import cn.edu.zust.se.dao.GameMapper;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.vo.ActivityVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public List<ActivityVo> getActivityVoById(int id) {
        List<ActivityVo> activityVos = activityMapper.selectActivityVoByClubId(id);
        for (ActivityVo activityVo : activityVos) {
            String s = activityMapper.selectActivityVoTagsById(activityVo.getId());
            activityVo.setNumber(activityMapper.selectActivityVoNumberById(activityVo.getId()));
            String[] ss = s.split(";");
            List<String> tags = new ArrayList<String>();
            for (String tag : ss) {
                tag = gameMapper.selectGameById(Integer.parseInt(tag)).getName();
                tags.add(tag);
            }
            activityVo.setTags(tags);
        }
        return activityVos;
    }
}
