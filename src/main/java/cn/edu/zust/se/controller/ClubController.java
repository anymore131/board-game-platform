package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.service.GameServiceI;
import cn.edu.zust.se.service.PictureServiceI;
import cn.edu.zust.se.vo.ClubPictureVo;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserVo;
import cn.edu.zust.se.vo.UserJoinVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
    GameServiceI gameService;
    @Resource
    PictureServiceI pictureService;

    @RequestMapping(value = "clubHome", method = RequestMethod.GET)
    public String clubHome(@RequestParam("clubId") String clubId,
                           HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (clubId == null) {
            return "redirect:/user/index";
        }
        ClubVo club = clubService.getClubVo(Integer.parseInt(clubId));
        club.setClubType(clubService.getClubType(user.getId(), club.getId()));
        club.setNumber(clubService.getClubJoinNumber(club.getId()));
        List<ClubPictureVo> clubPicture = pictureService.selectClubPictureByClubId(club.getId());
        if (club.getClubType() == 1) {
            List<UserJoinVo> userJoins = clubService.getUserJoinVoByClubId(club.getId());
            session.setAttribute("userJoins", userJoins);
        }
        session.setAttribute("pictures", clubPicture);
        session.setAttribute("club", club);
        return "clubHome";
    }

    @RequestMapping(value = "createClub", method = RequestMethod.GET)
    public String createClubGet(HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login/login";
        }
        List<String> games = gameService.selectAllGameName();
        session.setAttribute("games", games);
        return "createClub";
    }

    @RequestMapping(value = "createClub", method = RequestMethod.POST)
    public String createClubPost(HttpServletRequest request, HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login/login";
        }
        ClubBo club = new ClubBo();
        String[] tags = request.getParameterValues("tags");
        StringBuilder ss = new StringBuilder(";");
        for (String tag : tags) {
            ss.append(";").append(tag);
        }
        club.setClubName(request.getParameterValues("clubName")[0]);
        club.setIntroduction(request.getParameterValues("introduction")[0]);
        club.setTags(ss.toString());
        club.setProvince(request.getParameterValues("province")[0]);
        club.setCity(request.getParameterValues("city")[0]);
        club.setUserId(user.getId());
        if (clubService.insetClub(user.getId(), club)) {
            session.setAttribute("error", "添加成功");
            return "redirect:/user/index";
        }
        session.setAttribute("error", "信息有误");
        return "createClub";
    }
}
