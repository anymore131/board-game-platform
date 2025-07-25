package cn.edu.zust.se.service;

import java.util.List;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserJoinVo;

/**
 * @author Lenovo
 */
public interface ClubServiceI {
    ClubVo getClubVo(int id);

    ClubBo getClubBo(int id);

    List<ClubVo> getClubVoByUserJoin(int userId);

    List<ClubVo> getClubVoManageByUserJoin(int userId);

    Integer getClubNumberByName(String clubName);

    List<ClubVo> getClubVoByName(String clubName, int pageNo, int pageSize, int userId);

    Integer getClubNumberByTag(String tag);

    List<ClubVo> getClubVoByTag(String tag, int pageNo, int pageSize, int userId);

    boolean insetClub(int userId, ClubBo clubBo);

    Integer getClubType(int userId, int clubId);

    List<UserJoinVo> getUserJoinVoByClubId(int clubId);

    Integer getClubJoinNumber(int clubId);

    void updateClub(ClubBo clubBo);

    List<ClubVo> getClubVoByProvinceAndCity(String province, String city,int userId);

    void userJoinClub(int userId,int clubId);

    List<ClubVo> selectClubVoByVisible(int status);

    void setbisible(ClubBo clubBo);

    void userOutClub(int userId,int clubId);
}
