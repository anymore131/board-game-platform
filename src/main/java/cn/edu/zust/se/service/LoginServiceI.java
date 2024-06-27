package cn.edu.zust.se.service;

import cn.edu.zust.se.bo.UserBo;
import cn.edu.zust.se.vo.UserVo;

/**
 * @author Lenovo
 */
public interface LoginServiceI {
    public UserVo login(UserBo userBo);
    public UserVo register(UserBo userBo);
}
