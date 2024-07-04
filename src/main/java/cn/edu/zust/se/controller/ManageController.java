package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.service.GameServiceI;
import cn.edu.zust.se.service.UserServiceI;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.GameTypeVo;
import cn.edu.zust.se.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Date;
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
    @Resource
    GameServiceI gameService;
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
    @RequestMapping(value = "createGame" ,method = RequestMethod.GET)
    public String createGameGet() {
        return "createGame";
    }
    @RequestMapping(value = "createGame" ,method = RequestMethod.POST)
    public String createGamePost(HttpServletRequest request, HttpSession session) {
        GameTypeVo gameTypeVo = new GameTypeVo();
        gameTypeVo.setInsertTime(new Date(new java.util.Date().getTime()));
        gameTypeVo.setName(request.getParameterValues("name" )[0]);
        gameTypeVo.setIntroduction(request.getParameterValues("introduction")[0]);
        gameService.createGame(gameTypeVo.getName(),gameTypeVo.getIntroduction(),gameTypeVo.getInsertTime());
        if(gameService.getGameByName(gameTypeVo.getName())){
            session.setAttribute("error","创建成功！");
            return "index";
        }else {
            session.setAttribute("error","创建失败！");
            return "createGame";
        }
    }
}
