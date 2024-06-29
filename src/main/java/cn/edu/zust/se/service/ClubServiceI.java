package cn.edu.zust.se.service;

import java.util.List;
import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.vo.ClubVo;

/**
 * @author Lenovo
 */
public interface ClubServiceI {
    ClubVo getClubVo(int id);
    List<ClubVo> getClubVoByUserJoin(int userId);
    List<ClubVo> getClubVoManageByUserJoin(int userId);
    int getClubNumberByName(String clubName);
    List<ClubVo> getClubVoByName(String clubName,int pageNo,int pageSize,int userId);
    int getClubNumberByTag(String tag);
    List<ClubVo> getClubVoByTag(String tag,int pageNo,int pageSize,int userId);
}
