package cn.edu.zust.se.controller;

import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("club")
public class ClubController {
    @Resource
    ClubServiceI clubService;
    @Resource
    ActivityServiceI activityService;

    @RequestMapping(value = "clubHome",method = RequestMethod.GET)
    public String clubHome(@RequestParam("clubName")String clubName,
                           HttpSession session) {
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        if(clubName==null){
            return "redirect:/user/home";
        }

        return "clubHome";
    }
}
