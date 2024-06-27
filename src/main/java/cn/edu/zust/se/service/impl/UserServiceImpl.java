package cn.edu.zust.se.service.impl;

import cn.edu.zust.se.dao.UserMapper;
import cn.edu.zust.se.service.UserServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserServiceI {
    @Resource
    UserMapper userMapper;

}
