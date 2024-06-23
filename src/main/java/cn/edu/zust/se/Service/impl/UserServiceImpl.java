package cn.edu.zust.se.Service.impl;

import cn.edu.zust.se.Dao.UserMapper;
import cn.edu.zust.se.Service.UserServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserServiceI {
    @Resource
    UserMapper userMapper;

}
