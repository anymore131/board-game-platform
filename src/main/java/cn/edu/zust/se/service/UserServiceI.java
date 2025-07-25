package cn.edu.zust.se.service;

import cn.edu.zust.se.vo.UserVo;

import java.util.List;

/**
 * @author Lenovo
 */
public interface UserServiceI {
    List<Integer> getUserJoin(int id);
    UserVo selectUserById(int id);
    void updateUserAvatar(int id, String fname);
    List<UserVo> getUser(String userName, int pageNo, int pageSize);
    Integer getUserNumber(String userName);
    void updateUser(UserVo userVo);
    void grantUser(UserVo userVo);
    List<UserVo> selectAll();
//    List<String> getUserAttended(int userId);
    void deleteUser(int id);
}