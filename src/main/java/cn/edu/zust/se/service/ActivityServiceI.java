package cn.edu.zust.se.service;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.vo.ActivityVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lenovo
 */
public interface ActivityServiceI {
    List<ActivityVo> getActivityVoById(int id);
}
