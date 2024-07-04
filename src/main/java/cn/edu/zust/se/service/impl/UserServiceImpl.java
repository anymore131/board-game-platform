package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.ActivityMapper;
import cn.edu.zust.se.dao.ClubMapper;
import cn.edu.zust.se.dao.UserMapper;
import cn.edu.zust.se.service.UserServiceI;
import cn.edu.zust.se.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lenovo
 */
@Service
@Transactional
public class UserServiceImpl implements UserServiceI {
    @Resource
    UserMapper userMapper;
    @Resource
    ClubMapper clubMapper;
    @Resource
    ActivityMapper activityMapper;

    @Override
    public List<Integer> getUserJoin(int id) {
        return userMapper.selectUserJoinByUserId(id);
    }

    @Override
    public UserVo selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public void updateUserAvatar(int id, String fname) {
        userMapper.updateUserAvatar(id, fname);
    }

    @Override
    public List<UserVo> getUser(String userName, int pageNo,int pageSize) {
        return userMapper.getUserVoByUserName(userName,pageNo,pageSize);
    }

    @Override
    public Integer getUserNumber(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public void updateUser(UserVo userVo) {
        userMapper.updateUser(userVo);
    }

    @Override
    public void grantUser(UserVo userVo) {
        userMapper.updateAndGrantUser(userVo);
    }

    @Override
    public List<UserVo> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<String> getUserAttended(int userId) {
        return userMapper.selectUserAttended(userId);
    }

    @Override
    public void deleteUser(int id) {
//        userMapper.deleteUserActivityComments(id);
//        userMapper.deleteUserClubComments(id);
//        userMapper.deleteUserJoin(id);
//        userMapper.deleteUserAttended(id);
//        int[] n = clubMapper.selectClubByUserId(id);
//        for(int i = 1; i < n.length; i++){
//            activityMapper.deleteActivityByClubId(n[i]);
//        }
//        userMapper.deleteUserClub(id);
//        userMapper.deleteUser(id);
    }
}
