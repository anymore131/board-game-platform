package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.dao.ActivityMapper;
import cn.edu.zust.se.dao.ClubMapper;
import cn.edu.zust.se.dao.GameMapper;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.vo.ActivityVo;
import cn.edu.zust.se.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ClubMapper clubMapper;

    @Override
    public List<ActivityVo> getActivityVoByClubId(int clubId, int userId) {
        List<ActivityVo> activityVos = activityMapper.selectActivityVoByClubId(clubId);
        for (ActivityVo activityVo : activityVos) {
            String s = activityMapper.selectActivityVoTagsById(activityVo.getId());
            activityVo.setNumber(activityMapper.selectActivityVoNumberById(activityVo.getId()));
            if (s != null) {
                activityVo.setTags(splitTag(s.split(";")));
            }
            if (activityMapper.selectActivityByUserIdAndActivityId(userId, activityVo.getId()) != null) {
                activityVo.setAttended(1);
            } else {
                activityVo.setAttended(0);
            }
        }
        return activityVos;
    }

    @Override
    public Integer getActivityVoNumberByTag(String tag) {
        Date nowTime = new Date(new java.util.Date().getTime());
        return activityMapper.selectActivityNumberByTag(';' + String.valueOf(gameMapper.selectGameIdByName(tag)) + ';', nowTime);
    }

    @Override
    public List<ActivityVo> getActivityVoByTag(String tag, int pageNo, int pageSize, int userId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activityVos = activityMapper.selectActivityByTag
                (';' + String.valueOf(gameMapper.selectGameIdByName(tag)) + ';', pageNo, pageSize, nowTime);
        for (ActivityVo activityVo : activityVos) {
            String s = activityMapper.selectActivityVoTagsById(activityVo.getId());
            activityVo.setNumber(activityMapper.selectActivityVoNumberById(activityVo.getId()));
            if (s != null) {
                activityVo.setTags(splitTag(s.split(";")));
            }
            if (activityMapper.selectActivityByUserIdAndActivityId(userId, activityVo.getId()) != null) {
                activityVo.setAttended(1);
            } else {
                activityVo.setAttended(0);
            }
        }
        return activityVos;
    }

    @Override
    public Integer getActivityVoNumberByName(String name) {
        Date nowTime = new Date(new java.util.Date().getTime());
        return activityMapper.selectActivityNumberByName(name, nowTime);
    }

    @Override
    public List<ActivityVo> getActivityVoByName(String name, int pageNo, int pageSize, int userId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activityVos = activityMapper.selectActivityByName(name, pageNo, pageSize, nowTime);
        for (ActivityVo activityVo : activityVos) {
            String s = activityMapper.selectActivityVoTagsById(activityVo.getId());
            activityVo.setNumber(activityMapper.selectActivityVoNumberById(activityVo.getId()));
            if (s != null){
                activityVo.setTags(splitTag(s.split(";")));
            }
            if (activityMapper.selectActivityByUserIdAndActivityId(userId, activityVo.getId()) != null) {
                activityVo.setAttended(1);
            } else {
                activityVo.setAttended(0);
            }
        }
        return activityVos;
    }

    @Override
    public ActivityVo insertActivity(ActivityBo activityBo,int userId) {
        Integer id = activityMapper.selectActivityIdByClubIdAndActivityName(activityBo.getClubId(), activityBo.getActivityName());
        if (id == null) {
            activityMapper.insertActivity(activityBo);
            id = activityMapper.selectActivityIdByClubIdAndActivityName(activityBo.getClubId(), activityBo.getActivityName());
            ActivityVo activity = activityMapper.selectActivityById(id);
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            if(activityMapper.selectActivityVoTagsById(activity.getId())!=null){
                activity.setTags(splitTag(activityMapper.selectActivityVoTagsById(activity.getId()).split(";")));
            }
            activityMapper.insertUserAttend(userId,activity.getId());
            return activity;
        }
        return null;
    }

    @Override
    public void deleteActivity(int id) {
        activityMapper.deleteActivityComments(id);
        activityMapper.deleteActivity(id);
        activityMapper.deleteActivityById(id);
    }

    @Override
    public ActivityVo getActivityVoById(int id, int userId) {
        ActivityVo activity = activityMapper.selectActivityById(id);
        activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
        String s = activityMapper.selectActivityVoTagsById(activity.getId());
        if (s != null) {
            activity.setTags(splitTag(s.split(";")));
        }
        if (activityMapper.selectActivityByUserIdAndActivityId(userId, activity.getId()) != null) {
            activity.setAttended(1);
        } else {
            activity.setAttended(0);
        }
        return activity;
    }

    @Override
    public ActivityVo getActivityById(int id) {
        ActivityVo activity = activityMapper.selectActivityById(id);
        activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
        String s = activityMapper.selectActivityVoTagsById(activity.getId());
        if (s != null) {
            activity.setTags(splitTag(s.split(";")));
        }
        return activity;
    }

    @Override
    public List<UserVo> getUserAttend(int activityId) {
        return activityMapper.selectUserAttend(activityId);
    }

    @Override
    public void updateActivity(ActivityBo activityBo) {
        activityMapper.updateActivity(activityBo);
    }

    @Override
    public List<ActivityVo> listClubActivity(int clubId,int userId) {
        List<ActivityVo> activities = activityMapper.listClubActivity(clubId);
        for (ActivityVo activity : activities) {
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            String s = activityMapper.selectActivityVoTagsById(activity.getId());
            if (s != null) {
                activity.setTags(splitTag(s.split(";")));
            }
            if (activityMapper.selectActivityByUserIdAndActivityId(userId, activity.getId()) != null) {
                activity.setAttended(1);
            } else {
                activity.setAttended(0);
            }
        }
        return activities;
    }

    @Override
    public void userAttendActivity(int userId, int activityId) {
        activityMapper.insertUserAttend(userId,activityId);
    }

    @Override
    public void userQuitActivity(int userId, int activityId) {
        activityMapper.deleteUserAttend(userId,activityId);
    }

    @Override
    public Integer getActivityUnStartNumberByUserId(int userId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        Integer i = activityMapper.selectActivityUnStartNumberByUserId(userId,nowTime);
        if (i == null) {
            return 0;
        }
        return i;
    }

    @Override
    public List<ActivityVo> getActivityUnStartByUserId(int userId, int pageNo, int pageSize) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activities = activityMapper.selectActivityUnStartByUserId(userId,pageNo,pageSize,nowTime);
        for (ActivityVo activity : activities) {
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            String s = activityMapper.selectActivityVoTagsById(activity.getId());
            if (s != null) {
                activity.setTags(splitTag(s.split(";")));
            }
            activity.setAttended(1);
        }
        return activities;
    }

    @Override
    public Integer getActivityStartingNumberByUserId(int userId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        Integer i = activityMapper.selectActivityStartingNumberByUserId(userId,nowTime);
        if (i == null) {
            return 0;
        }
        return i;
    }

    @Override
    public List<ActivityVo> getActivityStartingByUserId(int userId, int pageNo, int pageSize) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activities = activityMapper.selectActivityStartingByUserId(userId,pageNo,pageSize,nowTime);
        for (ActivityVo activity : activities) {
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            String s = activityMapper.selectActivityVoTagsById(activity.getId());
            if (s != null) {
                activity.setTags(splitTag(s.split(";")));
            }
            activity.setAttended(1);
        }
        return activities;
    }

    @Override
    public Integer getActivityEndNumberByUserId(int userId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        Integer i =  activityMapper.selectActivityEndNumberByUserId(userId,nowTime);
        if (i == null) {
            return 0;
        }
        return i;
    }

    @Override
    public List<ActivityVo> getActivityEndByUserId(int userId, int pageNo, int pageSize) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activities = activityMapper.selectActivityEndByUserId(userId,pageNo,pageSize,nowTime);
        for (ActivityVo activity : activities) {
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            String s = activityMapper.selectActivityVoTagsById(activity.getId());
            if (s != null) {
                activity.setTags(splitTag(s.split(";")));
            }
            activity.setAttended(1);
        }
        return activities;
    }

    @Override
    public Integer getActivityUnStartNumberByClubId(int clubId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        Integer i = activityMapper.selectActivityUnStartNumberByClubId(clubId,nowTime);
        if (i == null) {
            return 0;
        }
        return i;
    }

    @Override
    public List<ActivityVo> getActivityUnStartByClubId(int clubId, int userId, int pageNo, int pageSize) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activities = activityMapper.selectActivityUnStartByClubId(clubId,pageNo,pageSize,nowTime);
        for (ActivityVo activity : activities) {
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            String s = activityMapper.selectActivityVoTagsById(activity.getId());
            if (s != null) {
                activity.setTags(splitTag(s.split(";")));
            }
            if (activityMapper.selectActivityByUserIdAndActivityId(userId, activity.getId()) != null) {
                activity.setAttended(1);
            } else {
                activity.setAttended(0);
            }
            if (clubMapper.selectClubTypeByUserIdAndClubId(userId, clubId) != null) {
                activity.setAttended(clubMapper.selectClubTypeByUserIdAndClubId(userId, clubId));
            }
        }
        return activities;
    }

    @Override
    public Integer getActivityStartingNumberByClubId(int clubId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        Integer i = activityMapper.selectActivityStartingNumberByClubId(clubId,nowTime);
        if (i == null) {
            return 0;
        }
        return i;
    }

    @Override
    public List<ActivityVo> getActivityStartingByClubId(int clubId, int userId, int pageNo, int pageSize) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activities = activityMapper.selectActivityStartingByClubId(clubId,pageNo,pageSize,nowTime);
        for (ActivityVo activity : activities) {
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            String s = activityMapper.selectActivityVoTagsById(activity.getId());
            if (s != null) {
                activity.setTags(splitTag(s.split(";")));
            }
            if (activityMapper.selectActivityByUserIdAndActivityId(userId, activity.getId()) != null) {
                activity.setAttended(1);
            } else {
                activity.setAttended(0);
            }
            if (clubMapper.selectClubTypeByUserIdAndClubId(userId, clubId) != null) {
                activity.setAttended(clubMapper.selectClubTypeByUserIdAndClubId(userId, clubId));
            }
        }
        return activities;
    }

    @Override
    public Integer getActivityEndNumberByClubId(int clubId) {
        Date nowTime = new Date(new java.util.Date().getTime());
        Integer i = activityMapper.selectActivityEndNumberByClubId(clubId,nowTime);
        if (i == null) {
            return 0;
        }
        return i;
    }

    @Override
    public List<ActivityVo> getActivityEndByClubId(int clubId, int userId, int pageNo, int pageSize) {
        Date nowTime = new Date(new java.util.Date().getTime());
        List<ActivityVo> activities = activityMapper.selectActivityEndByClubId(clubId,pageNo,pageSize,nowTime);
        for (ActivityVo activity : activities) {
            activity.setNumber(activityMapper.selectActivityVoNumberById(activity.getId()));
            String s = activityMapper.selectActivityVoTagsById(activity.getId());
            if (s != null) {
                activity.setTags(splitTag(s.split(";")));
            }
            if (activityMapper.selectActivityByUserIdAndActivityId(userId, activity.getId()) != null) {
                activity.setAttended(1);
            } else {
                activity.setAttended(0);
            }
            if (clubMapper.selectClubTypeByUserIdAndClubId(userId, clubId) != null) {
                activity.setAttended(clubMapper.selectClubTypeByUserIdAndClubId(userId, clubId));
            }
        }
        return activities;
    }

    public List<String> splitTag(String[] ss) {
        List<String> tags = new ArrayList<>();
        for (String tag : ss) {
            if (tag != null && !tag.isEmpty()) {
                tag = gameMapper.selectGameById(Integer.parseInt(tag)).getName();
                tags.add(tag);
            }
        }
        return tags;
    }
}
