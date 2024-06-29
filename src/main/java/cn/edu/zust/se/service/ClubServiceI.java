package cn.edu.zust.se.service;

import java.util.List;
import cn.edu.zust.se.vo.ClubVo;

/**
 * @author Lenovo
 */
public interface ClubServiceI {
    ClubVo getClubVo(int id);
    List<ClubVo> getClubVoByUserJoin(int userId);
    List<ClubVo> getClubVoManageByUserJoin(int userId);
    Integer getClubNumberByName(String clubName);
    List<ClubVo> getClubVoByName(String clubName,int pageNo,int pageSize,int userId);
    Integer getClubNumberByTag(String tag);
    List<ClubVo> getClubVoByTag(String tag,int pageNo,int pageSize,int userId);
}
