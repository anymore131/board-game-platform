package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.service.*;
import cn.edu.zust.se.util.Constants;
import cn.edu.zust.se.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.edu.zust.se.util.Constants.PAGE_SIZE;

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
    GameServiceI gameService;
    @Resource
    CommentsServiceI commentsService;
    @Resource
    PictureServiceI pictureService;

    @RequestMapping(value = "clubHome", method = RequestMethod.GET)
    public String clubHome(@RequestParam(value = "clubId",required = false) String clubId,
                           @RequestParam(value = "commentsPageNo",required = false) String commentsPageNo,
                           HttpServletRequest request,
                           HttpSession session) {
        cleanSession(session);
        UserVo user = (UserVo) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (clubId == null) {
            return "redirect:/user/index";
        }
        ClubVo club = clubService.getClubVo(Integer.parseInt(clubId));
        if(clubService.getClubType(user.getId(), club.getId())!=null){
            club.setJoined(1);
            club.setClubType(clubService.getClubType(user.getId(), club.getId()));
        }else{
            club.setJoined(0);
        }
        club.setNumber(clubService.getClubJoinNumber(club.getId()));
        List<ClubPictureVo> clubPicture = new ArrayList<>();
        if (club.getClubType() == 1) {
            List<UserJoinVo> userJoins = clubService.getUserJoinVoByClubId(club.getId());
            session.setAttribute("userJoins", userJoins);
        }
        if (pictureService.selectClubPictureCountByClubId(club.getId()) != 0) {
            clubPicture = pictureService.selectClubPictureByClubId(club.getId());
            session.setAttribute("pictures", clubPicture);
        }
        List<ActivityVo> activities = activityService.listClubActivity(club.getId(), user.getId());
        if (!activities.isEmpty()){
            session.setAttribute("activities",activities);
        }
        session.setAttribute("club", club);
        if (commentsPageNo == null || commentsPageNo.isEmpty()){
            commentsPageNo = "1";
        }
        String pageNo1 = request.getParameter("pageNo1");
        String pageNo2 = request.getParameter("pageNo2");
        String pageNo3 = request.getParameter("pageNo3");
        if (pageNo1 == null || pageNo1.isEmpty()){
            pageNo1 = "1";
        }
        if (pageNo2 == null || pageNo2.isEmpty()){
            pageNo2 = "1";
        }
        if (pageNo3 == null || pageNo3.isEmpty()){
            pageNo3 = "1";
        }
        showActivity(session,Integer.parseInt(pageNo1),Integer.parseInt(pageNo2),Integer.parseInt(pageNo3));
        showComments(session, Integer.parseInt(commentsPageNo));
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
            ss.append(";").append(gameService.getGameTypeId(tag));
        }
        ss.append(";");
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

    @RequestMapping(value = "changeClub",method = RequestMethod.GET)
    public String changeClubGet(HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        ClubVo club = (ClubVo) session.getAttribute("club");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (club == null) {
            return "redirect:/user/index";
        }
        List<String> games = gameService.selectAllGameName();
        session.setAttribute("games", games);
        return "changeClub";
    }

    @RequestMapping(value = "changeClub",method = RequestMethod.POST)
    public String changeClubPost(HttpServletRequest request, HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        ClubVo club = (ClubVo) session.getAttribute("club");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (club == null) {
            return "redirect:/user/index";
        }
        ClubBo clubBo = new ClubBo();
        clubBo.setId(club.getId());
        clubBo.setClubName(request.getParameterValues("clubName")[0]);
        clubBo.setIntroduction(request.getParameterValues("introduction")[0]);
        String[] tags = request.getParameterValues("tags");
        StringBuilder ss = new StringBuilder(";");
        for (String tag : tags) {
            ss.append(";").append(gameService.getGameTypeId(tag));
        }
        ss.append(";");
        clubBo.setTags(ss.toString());
        clubBo.setProvince(request.getParameterValues("province")[0]);
        clubBo.setCity(request.getParameterValues("city")[0]);
        clubBo.setUserId(user.getId());
        clubService.updateClub(clubBo);
        return "redirect:/club/clubHome?clubId=" + club.getId();
    }

    @RequestMapping(value = "activityCancel")
    public String activityCancel(HttpSession session){
        ActivityVo activity = (ActivityVo) session.getAttribute("activity");
        ClubVo club = (ClubVo) session.getAttribute("club");
        activityService.deleteActivity(activity.getId());
        return "redirect:/club/clubHome?clubId=" + club.getId();
    }

    @RequestMapping(value = "uploadClubPicture")
    public String uploadClubPictureGet(HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        ClubVo club = (ClubVo) session.getAttribute("club");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (club == null) {
            return "redirect:/user/index";
        }
        return "uploadClubPicture";
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
        ClubVo club = (ClubVo) session.getAttribute("club");
        if (user == null) {
            return "redirect:/login/login";
        }
        if (club == null) {
            return "redirect:/user/index";
        }
        if (picture.isEmpty()) {
            session.setAttribute("error","添加失败");
            return "redirect:/club/clubHome?clubId=" + club.getId();
        }
        String fileName = picture.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        String fileName2 = user.getUserName() + res + fileName;
        String finalPath = Constants.IMG_PATH2 + fileName2;
        java.sql.Date uploadTime =  new java.sql.Date(new Date().getTime());
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
        pictureService.insertClubPicture(name,fileName2,club.getId());
        session.setAttribute("error","添加成功");
        return "redirect:/club/uploadClubPicture";
    }

    @RequestMapping(value = "insertComments" , method = RequestMethod.POST)
    public String insertCommentsPost(HttpServletRequest request, HttpSession session) {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login/login";
        }
        ClubVo club = (ClubVo) session.getAttribute("club");
        String comments= request.getParameter("comments-text");
        if (comments==null || comments.isEmpty()){
            session.setAttribute("error","评论不能为空！");
            return "redirect:/club/clubHome?clubId=" + club.getId();
        }
        commentsService.postClubComments(user.getId(), club.getId(),comments);
        return "redirect:/club/clubHome?clubId=" + club.getId();
    }

    private void cleanSession(HttpSession session){
        session.removeAttribute("activities");
        session.removeAttribute("clubs");
        session.removeAttribute("manageClubs");
        session.removeAttribute("activity");
        session.removeAttribute("club");
        session.removeAttribute("pictures");
        session.removeAttribute("tjClubs");
        session.removeAttribute("activitiesUnStart");
        session.removeAttribute("activitiesStarting");
        session.removeAttribute("activitiesEnd");
    }

    private void showComments(HttpSession session,int commentsPageNo){
        ClubVo club = (ClubVo) session.getAttribute("club");
        if (club != null) {
            int maxCommentsPage = commentsService.getClubCommentsCountByClubId(club.getId()) / PAGE_SIZE + 1;
            session.setAttribute("maxCommentsPage", maxCommentsPage);
            if (commentsPageNo > maxCommentsPage){
                commentsPageNo = maxCommentsPage;
            }else if (commentsPageNo <= 0){
                commentsPageNo = 1;
            }
            if(commentsService.getClubCommentsCountByClubId(club.getId()) > 0){
                List<ClubCommentsVo> comments = commentsService.getClubCommentsByClubId(club.getId(),commentsPageNo,PAGE_SIZE);
                session.setAttribute("comments", comments);
            }
            session.setAttribute("commentsPageNo", commentsPageNo);
        }
    }

    private void showActivity(HttpSession session ,int pageNo1,int pageNo2,int pageNo3){
        UserVo user = (UserVo)session.getAttribute("user");
        ClubVo club = (ClubVo)session.getAttribute("club");
        List<ActivityVo> activitiesUnStart = new ArrayList<>();
        List<ActivityVo> activitiesStarting = new ArrayList<>();
        List<ActivityVo> activitiesEnd = new ArrayList<>();
        //还没开始的
        int maxPageNo1 = activityService.getActivityUnStartNumberByClubId(club.getId()) / PAGE_SIZE + 1;
        //正在进行中的
        int maxPageNo2 = activityService.getActivityStartingNumberByClubId(club.getId()) / PAGE_SIZE + 1;
        //结束的
        int maxPageNo3 = activityService.getActivityEndNumberByClubId(club.getId()) / PAGE_SIZE + 1;
        //标准化页码
        if (pageNo1 <= 0){
            pageNo1 = 1;
        }
        if (pageNo2 <= 0){
            pageNo2 = 1;
        }
        if (pageNo3 <= 0){
            pageNo3 = 1;
        }
        if (pageNo1 > maxPageNo1){
            pageNo1 = maxPageNo1;
        }
        if (pageNo2 > maxPageNo2){
            pageNo2 = maxPageNo2;
        }
        if (pageNo3 > maxPageNo3){
            pageNo3 = maxPageNo3;
        }
        if (activityService.getActivityUnStartNumberByClubId(club.getId()) != 0){
            activitiesUnStart = activityService.getActivityUnStartByClubId(club.getId(),user.getId(), pageNo1,PAGE_SIZE);
            session.setAttribute("activitiesUnStart", activitiesUnStart);
        }
        if (activityService.getActivityStartingNumberByClubId(club.getId()) != 0){
            activitiesStarting = activityService.getActivityStartingByClubId(club.getId(),user.getId(), pageNo2,PAGE_SIZE);
            session.setAttribute("activitiesStarting", activitiesStarting);
        }
        if (activityService.getActivityEndNumberByClubId(club.getId()) != 0){
            activitiesEnd = activityService.getActivityEndByClubId(club.getId(),user.getId(), pageNo3,PAGE_SIZE);
            session.setAttribute("activitiesEnd",activitiesEnd);
        }
        session.setAttribute("maxPageNo1", maxPageNo1);
        session.setAttribute("maxPageNo2", maxPageNo2);
        session.setAttribute("maxPageNo3", maxPageNo3);
        session.setAttribute("pageNo1", pageNo1);
        session.setAttribute("pageNo2", pageNo2);
        session.setAttribute("pageNo3", pageNo3);
    }
}
