package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.service.UserServiceI;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("manage")
public class ManageController {
    @Resource
    UserServiceI userService;
    @Resource
    ClubServiceI clubService;
    @Resource
    ActivityServiceI activityService;
    @RequestMapping(value = "administrator" ,method = RequestMethod.GET)
    public String administratorGet(HttpSession session,
                                @RequestParam(value = "userId", required = false) int userId) {
        List<ClubVo> newClubs = clubService.selectClubVoByVisible(0);
        session.setAttribute("newClubs", newClubs);
        session.setAttribute("userId", userId);
        return "administrator";
    }
    @RequestMapping(value = "administrator" ,method = RequestMethod.POST)
    public String administratorPet(HttpSession session,
                              @RequestParam(value = "userId", required = false) int userId,
                               @RequestParam(value = "id", required = false) int id ) {
//        System.out.println(id);
//        System.out.println(userId);
        ClubBo club=clubService.getClubBo(id);
        clubService.setbisible(club);
        List<ClubVo> newClubs = clubService.selectClubVoByVisible(0);
        session.setAttribute("newClubs", newClubs);
        session.setAttribute("userId", userId);
        return "administrator";
    }
}
