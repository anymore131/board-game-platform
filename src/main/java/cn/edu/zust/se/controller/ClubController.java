package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.service.PictureServiceI;
import cn.edu.zust.se.vo.ClubPictureVo;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserVo;
import cn.edu.zust.se.vo.UserJoinVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    PictureServiceI pictureService;

    @RequestMapping(value = "clubHome",method = RequestMethod.GET)
    public String clubHome(@RequestParam("clubId")String clubId,
                           HttpSession session) {
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        if(clubId==null){
            return "redirect:/user/index";
        }
        ClubVo club = clubService.getClubVo(Integer.parseInt(clubId));
        club.setClubType(clubService.getClubType(user.getId(), club.getId()));
        club.setNumber(clubService.getClubJoinNumber(club.getId()));
        List<ClubPictureVo> clubPicture = pictureService.selectClubPictureByClubId(club.getId());
        if(club.getClubType() == 1){
            List<UserJoinVo> userJoins = clubService.getUserJoinVoByClubId(club.getId());
            session.setAttribute("userJoins", userJoins);
        }
        session.setAttribute("picture", clubPicture);
        session.setAttribute("club", club);
        return "clubHome";
    }

    @RequestMapping(value = "createClub",method = RequestMethod.GET)
    public String createClubGet(HttpSession session) {
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        return "createClub";
    }
    @RequestMapping(value = "createClub",method = RequestMethod.POST)
    public String createClubPost(ClubBo clubBo, HttpSession session) {
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        if (clubService.insetClub(clubBo)){
            session.setAttribute("error", "添加成功");
            return "redirect:/user/userHome";
        }
        return "redirect:/user/index";
    }
}
