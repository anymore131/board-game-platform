package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.bo.UserBo;
import cn.edu.zust.se.dao.UserMapper;
import cn.edu.zust.se.service.LoginServiceI;
import cn.edu.zust.se.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * @author Lenovo
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginServiceI {
    @Resource
    UserMapper userMapper;

    @Override
    public UserVo login(UserBo userBo) {
        return userMapper.selectUserByUserNameAndPassword((userBo.getUserName()),(userBo.getPassword()));
    }

    @Override
    public UserVo register(UserBo userBo) {
        if (userMapper.selectUserByUserName(userBo.getUserName()) == null){
            userMapper.insertUser(userBo.getUserName(), userBo.getPassword(),userBo.getName(),
                    new Date(new java.util.Date().getTime()),"fls.jpg");
            return userMapper.selectUserByUserNameAndPassword(userBo.getUserName(),userBo.getPassword());
        }
        return null;
    }
}
