package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.service.*;
import cn.edu.zust.se.util.Constants;
import cn.edu.zust.se.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.zust.se.util.Constants.PAGE_SIZE;

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
    CommentsServiceI commentsService;
    @Resource
    ClubServiceI clubService;
    @Resource
    PictureServiceI pictureService;


    @RequestMapping(value = "activityHome",method = RequestMethod.GET)
    public String activityHomeGet(@RequestParam(value = "activityId")int activityId,
                                  @RequestParam(value = "commentsPageNo",required = false) String commentsPageNo,
                                  HttpSession session){
        cleanSession(session);
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        ActivityVo activity = activityService.getActivityVoById(activityId, user.getId());
        ClubVo club = clubService.getClubVo(activity.getClubId());
        if(clubService.getClubType(user.getId(), club.getId()) == null){
            club.setJoined(0);
        }else{
            club.setJoined(1);
            club.setClubType(clubService.getClubType(user.getId(), club.getId()));
        }
        if (commentsPageNo == null || commentsPageNo.isEmpty()){
            commentsPageNo = "1";
        }
        if (clubService.getClubType(user.getId(), club.getId()) != null){
            activity.setClubType(clubService.getClubType(user.getId(), club.getId()));
        }
        List<ActivityPictureVo> activityPicture = new ArrayList<>();
        if (pictureService.selectActivityPictureCountByActivityId(activityId) != 0){
            activityPicture = pictureService.selectActivityPictureByActivityId(activityId);
            session.setAttribute("pictures", activityPicture);
        }
        List<UserVo> users = activityService.getUserAttend(activityId);
        session.setAttribute("userAttends", users);
        session.setAttribute("activity", activity);
        session.setAttribute("club", club);
        showComments(session, Integer.parseInt(commentsPageNo));
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
                ss.append(";").append(gameService.getGameTypeId(tag));
            }
            ss.append(";");
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

    @RequestMapping(value = "changeActivity",method = RequestMethod.GET)
    public String changeActivityGet(HttpServletRequest request,HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (request.getParameter("activityId") == null){
            return "redirect:/user/index";
        }
        ActivityVo activity = activityService.getActivityById(Integer.parseInt(request.getParameter("activityId")));
        session.setAttribute("activity", activity);
        List<String> games = gameService.selectAllGameName();
        session.setAttribute("games", games);
        return "changeActivity";
    }

    @RequestMapping(value = "changeActivity",method = RequestMethod.POST)
    public String changeActivityPost(HttpServletRequest request, HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        if (user == null) {
            return "redirect:/login/login";
        }
        ActivityBo activityBo = new ActivityBo();
        activityBo.setId(activity.getId());
        activityBo.setClubId(activityService.getActivityById(activity.getId()).getClubId());
        activityBo.setActivityName(request.getParameter("activityName"));
        activityBo.setIntroduction(request.getParameter("introduction"));
        activityBo.setAddress(request.getParameter("address"));
        if (request.getParameter("endTime") == null||request.getParameter("startTime")==null){
            session.setAttribute("error","活动时间设置为空！");
            return "changeActivity";
        }
        if(Date.valueOf(request.getParameter("endTime")).before(Date.valueOf(request.getParameter("startTime")))){
            session.setAttribute("error","开始时间不能在结束时间之前！");
            return "changeActivity";
        }
        else if(Date.valueOf(request.getParameter("startTime")).before(new Date(new java.util.Date().getTime()))){
            session.setAttribute("error","开始时间不能在当前时间之前！");
            return "changeActivity";
        }

        activityBo.setEndTime(Date.valueOf(request.getParameter("endTime")));
        activityBo.setStartTime(Date.valueOf(request.getParameter("startTime")));

        String[] tags = request.getParameterValues("tags");
        StringBuilder ss = new StringBuilder(";");
        for (String tag : tags) {
            ss.append(";").append(gameService.getGameTypeId(tag));
        }
        ss.append(";");
        activityBo.setTags(ss.toString());
        activityService.updateActivity(activityBo);
        session.setAttribute("activity", activityBo);
        session.setAttribute("error","更改成功！");
        return "redirect:/activity/activityHome?activityId=" + activityBo.getId();
    }

    @RequestMapping(value = "attendIn")
    public String attendInGet(HttpServletRequest request, HttpSession session) {
        UserVo user = (UserVo)session.getAttribute("user");
        ClubVo club = (ClubVo)session.getAttribute("club");
        if(user == null){
            return "redirect:/login/login";
        }
        if(club == null){
            return "redirect:/user/index";
        }
        String activityId = request.getParameter("activityId");
        activityService.userAttendActivity(user.getId(),Integer.parseInt(activityId));
        return "redirect:/club/clubHome?clubId="+club.getId();
    }

    @RequestMapping(value = "attendOut")
    public String attendOutGet(HttpServletRequest request,HttpSession session) {
        UserVo user = (UserVo)session.getAttribute("user");
        ClubVo club = (ClubVo)session.getAttribute("club");
        if(user == null){
            return "redirect:/login/login";
        }
        if(club == null){
            return "redirect:/user/index";
        }
        String activityId = request.getParameter("activityId");
        activityService.userQuitActivity(user.getId(),Integer.parseInt(activityId));
        return "redirect:/club/clubHome?clubId="+club.getId();
    }

    @RequestMapping(value = "/userAttend")
    public String userAttend(HttpSession session){
        UserVo user = (UserVo) session.getAttribute("user");
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        activityService.userAttendActivity(user.getId(),activity.getId());
        return "redirect:/activity/activityHome?activityId=" + activity.getId();
    }

    @RequestMapping(value = "/userQuit")
    public String userQuit(HttpSession session){
        UserVo user = (UserVo) session.getAttribute("user");
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        activityService.userQuitActivity(user.getId(),activity.getId());
        return "redirect:/activity/activityHome?activityId=" + activity.getId();
    }

    @RequestMapping(value = "uploadActivityPicture")
    public String uploadClubPictureGet(HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (activity == null) {
            return "redirect:/user/index";
        }
        return "uploadActivityPicture";
    }

    private void cleanSession(HttpSession session) {
        session.removeAttribute("comments");
        session.removeAttribute("pictures");
        session.removeAttribute("club");
        session.removeAttribute("game");
        session.removeAttribute("clubs");
        session.removeAttribute("activities");
        session.removeAttribute("activity");
    }

    @RequestMapping(value = "insertActivityComments" , method = RequestMethod.POST)
    public String insertComments(HttpServletRequest request, HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login/login";
        }
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        commentsService.postActivityComments(user.getId(), activity.getId(), request.getParameter("comments-text"));
        return "redirect:/activity/activityHome?activityId=" + activity.getId();
    }

    /**
     * 上传文件
     * @param picture       图片文件
     * @param session       session
     * @return              返回到uploadPicture
     */
    @RequestMapping("/upload")
    public String testUpload(@RequestParam("picture") MultipartFile picture,
                             HttpSession session) throws IOException {
        UserVo user = (UserVo) session.getAttribute("user");
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (activity == null) {
            return "redirect:/user/index";
        }
        if (picture.isEmpty()) {
            session.setAttribute("error","添加失败");
            return "redirect:/activity/activityHome?activity=" + activity.getId();
        }
        String fileName = picture.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new java.util.Date());
        String fileName2 = user.getUserName() + res + fileName;
        String finalPath = Constants.IMG_PATH2 + fileName2;
        java.sql.Date uploadTime =  new java.sql.Date(new java.util.Date().getTime());
        int nameIndex = fileName.lastIndexOf(".");
        String name;
        if (nameIndex > 0 && nameIndex < fileName.length() - 1){
            name = fileName.substring( 0, nameIndex);
        }else {
            name = fileName;
        }
        // 验证服务器是否已经创建了该目录
        File fileSave = new File(Constants.IMG_PATH2);
        // 判断保存路径文件对象是否存在
        if (!fileSave.exists()) {
            // 创建该目录
            fileSave.mkdirs();
        }
        try{
            byte[] bytes = picture.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);
//            用不了transferTo，不知道为什么
//            picture.transferTo(new File(finalPath));
        }catch (Exception e){
            e.printStackTrace();
        }
        pictureService.insertActivityPicture(name,fileName2,activity.getId());
        session.setAttribute("error","添加成功");
        return "redirect:/activity/uploadActivityPicture";
    }

    private void showComments(HttpSession session,int commentsPageNo){
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        if (activity != null) {
            int maxCommentsPage =commentsService.getActivityCommentsCountByActivityId(activity.getId()) / PAGE_SIZE + 1;
            session.setAttribute("maxCommentsPage", maxCommentsPage);
            if (commentsPageNo > maxCommentsPage){
                commentsPageNo = maxCommentsPage;
            }else if (commentsPageNo <= 0){
                commentsPageNo = 1;
            }
            if(commentsService.getActivityCommentsCountByActivityId(activity.getId()) > 0){
                List<ActivityCommentsVo> comments = commentsService.getActivityCommentsByActivityId(activity.getId(),commentsPageNo,PAGE_SIZE);
                session.setAttribute("comments", comments);
            }
            session.setAttribute("commentsPageNo", commentsPageNo);
        }
    }
}