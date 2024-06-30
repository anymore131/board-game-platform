package cn.edu.zust.se.service;

import cn.edu.zust.se.vo.ActivityPictureVo;
import cn.edu.zust.se.vo.ClubPictureVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface PictureServiceI {
    void insertClubPicture(String name, String fname, int clubId);

    void insertActivityPicture(String name, String fname, int activityId);

    List<ClubPictureVo> selectClubPictureByClubId(int clubId);

    List<ActivityPictureVo> selectActivityPictureByActivityId(int activityId);
}
