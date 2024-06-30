package cn.edu.zust.se.service;

import cn.edu.zust.se.bo.UserBo;
import cn.edu.zust.se.vo.UserVo;

/**
 * @author Lenovo
 */
public interface LoginServiceI {
    UserVo login(UserBo userBo);

    UserVo register(UserBo userBo);
}
