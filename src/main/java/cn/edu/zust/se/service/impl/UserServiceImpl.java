package cn.edu.zust.se.service.impl;

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

    @Override
    public List<Integer> getUserJoin(int id) {
        return userMapper.selectUserJoinByUserId(id);
    }

    @Override
    public UserVo selectuserbyid(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public void updateUserAvatar(int id, String fname) {
        userMapper.updateUserAvatar(id, fname);
    }

    @Override
    public List<UserVo> getUser(String userName, int pageNo,int pageSize) {
        System.out.println(userMapper.getUserVoByUserName(userName,pageNo,pageSize));
        return userMapper.getUserVoByUserName(userName,pageNo,pageSize);
    }

    @Override
    public Integer getUserNumber(String userName) {
        System.out.println(userMapper.getUserByUserName(userName));
        return userMapper.getUserByUserName(userName);
    }
}
