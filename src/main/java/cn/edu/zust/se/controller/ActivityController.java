package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.service.GameServiceI;
import cn.edu.zust.se.vo.ActivityVo;
import cn.edu.zust.se.vo.ClubVo;
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

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("activity")
public class ActivityController {
    @Resource
    ActivityServiceI activityService;
    @Resource
    GameServiceI gameService;
    @Resource
    ClubServiceI clubService;

    @RequestMapping(value = "activityHome",method = RequestMethod.GET)
    public String activityHomeGet(@RequestParam(value = "activityId")int activityId,
                                  HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        cleanSession(session);
        ActivityVo activity = activityService.getActivityVoById(activityId, user.getId());
        ClubVo club = clubService.getClubVo(activity.getClubId());
        if(clubService.getClubType(user.getId(), club.getId()) == null){
            club.setJoined(0);
        }else{
            club.setJoined(1);
            club.setClubType(clubService.getClubType(user.getId(), club.getId()));
        }
        session.setAttribute("activity", activity);
        session.setAttribute("club", club);
        return "activityHome";
    }

    @RequestMapping(value = "createActivity",method = RequestMethod.POST)
    public String createActivityPost(HttpServletRequest request, HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        ClubVo club = (ClubVo)session.getAttribute("club");
        if(user == null){
            return "redirect:/login/login";
        }
        if(club == null){
            return "redirect:/user/index";
        }
        ActivityBo activityBo = new ActivityBo();
        activityBo.setClubId(club.getId());
        if (request.getParameter("activityName")==null){
            session.setAttribute("error","活动名设置为空！");
            return "createActivity";
        }
        activityBo.setActivityName(request.getParameter("activityName"));
        if (request.getParameter("introduction")==null){
            activityBo.setIntroduction(null);
        }else{
            activityBo.setIntroduction(request.getParameter("introduction"));
        }
        if (request.getParameterValues("tags")==null){
            activityBo.setTags(null);
        }else{
            String[] tags = request.getParameterValues("tags");
            StringBuilder ss = new StringBuilder(";");
            for (String tag : tags) {
                ss.append(";").append(tag);
            }
            activityBo.setTags(ss.toString());
        }
        if (request.getParameter("endTime") == null||request.getParameter("startTime")==null){
            session.setAttribute("error","活动时间设置为空！");
            return "createActivity";
        }
        if(Date.valueOf(request.getParameter("endTime")).before(Date.valueOf(request.getParameter("startTime")))){
            session.setAttribute("error","时间设置错误1！");
            return "createActivity";
        }
        else if(Date.valueOf(request.getParameter("startTime")).before(new Date(new java.util.Date().getTime()))){
            session.setAttribute("error","时间设置错误2！");
            return "createActivity";
        }
        activityBo.setStartTime(Date.valueOf(request.getParameter("startTime")));
        activityBo.setEndTime(Date.valueOf(request.getParameter("endTime")));
        activityBo.setCreateTime(new Date(new java.util.Date().getTime()));
        ActivityVo activityVo = activityService.insertActivity(activityBo,user.getId());
        if(activityVo != null){
            session.setAttribute("error","添加成功");
            return "redirect:/club/clubHome?clubId="+club.getId();
        }
        session.setAttribute("error","添加失败！");
        return "createActivity";
    }

    @RequestMapping(value = "createActivity", method = RequestMethod.GET)
    public String createActivityGet(HttpSession session) {
        UserVo user = (UserVo)session.getAttribute("user");
        ClubVo club = (ClubVo)session.getAttribute("club");
        if(user == null){
            return "redirect:/login/login";
        }
        if(club == null){
            return "redirect:/user/index";
        }
        List<String> games = gameService.selectAllGameName();
        session.setAttribute("games", games);
        return "createActivity";
    }

    private void cleanSession(HttpSession session) {
        session.removeAttribute("comments");
        session.removeAttribute("pictures");
        session.removeAttribute("club");
        session.removeAttribute("game");
        session.removeAttribute("activity");
    }
}