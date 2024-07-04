package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.PictureMapper;
import cn.edu.zust.se.service.PictureServiceI;
import cn.edu.zust.se.vo.ActivityPictureVo;
import cn.edu.zust.se.vo.ClubPictureVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lenovo
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureServiceI {
    @Resource
    PictureMapper pictureMapper;

    @Override
    public void insertClubPicture(String name, String fname, int clubId) {
        pictureMapper.insertClubPicture(name, fname, clubId);
    }

    @Override
    public void insertActivityPicture(String name, String fname, int activityId) {
        pictureMapper.insertActivityPicture(name, fname, activityId);
    }

    @Override
    public List<ClubPictureVo> selectClubPictureByClubId(int clubId) {
        return pictureMapper.selectClubPictureVoByClubId(clubId);
    }

    @Override
    public List<ActivityPictureVo> selectActivityPictureByActivityId(int activityId) {
        return pictureMapper.selectActivityPictureVoByActivityId(activityId);
    }

    @Override
    public Integer selectClubPictureCountByClubId(int clubId) {
        return pictureMapper.selectClubPictureCountByClubId(clubId);
    }

    @Override
    public Integer selectActivityPictureCountByActivityId(int activityId) {
        return pictureMapper.selectActivityPictureCountByActivityId(activityId);
    }
}
