package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ClubBo;
import cn.edu.zust.se.bo.UserBo;
import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.service.UserServiceI;
import cn.edu.zust.se.util.Constants;
import cn.edu.zust.se.vo.ActivityVo;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static cn.edu.zust.se.util.Constants.PAGE_SIZE;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserServiceI userService;
    @Resource
    ClubServiceI clubService;
    @Resource
    ActivityServiceI activityService;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String indexGet(HttpServletRequest request,HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        if (user.getProvince() == null || user.getProvince().isEmpty() ||
                user.getCity() == null || user.getCity().isEmpty()) {
            session.setAttribute("error", "请先完善你的信息");
            return "userHome";
        }
        List<ClubVo> tjClubs = clubService.getClubVoByProvinceAndCity(user.getProvince(),user.getCity(), user.getId());
        List<ClubVo> clubs = new ArrayList<>();
        List<ActivityVo> activities = new ArrayList<>();
        for(Integer id : userService.getUserJoin(user.getId())){
            if (clubService.getClubVo(id) != null){
                clubs.add(clubService.getClubVo(id));
            }
        }
        for(ClubVo club : clubs){
            if (club!=null){
                activities.addAll(activityService.getActivityVoByClubId(club.getId(),user.getId()));
            }
        }
        for(ClubVo club : tjClubs){
            club.setNumber(clubService.getClubJoinNumber(club.getId()));
        }
        session.setAttribute("tjClubs",tjClubs);
        session.setAttribute("activities", activities);
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
        return "index";
    }

    @RequestMapping(value = "index",method = RequestMethod.POST)
    public String indexPost(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        cleanSession(session);
        return "index";
    }

    @RequestMapping(value = "userHome" ,method = RequestMethod.GET)
    public String userHomeGet(HttpServletRequest request,HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        List<ClubVo> clubs = clubService.getClubVoByUserJoin(user.getId());
        if (!clubs.isEmpty()){
            session.setAttribute("clubs", clubs);
        }
        List<ClubVo> manageClubs = clubService.getClubVoManageByUserJoin(user.getId());
        if (!manageClubs.isEmpty()){
            session.setAttribute("manageClubs", manageClubs);
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
        return "userHome";
    }

    @RequestMapping(value = "userHome" ,method = RequestMethod.POST)
    public String userHomePost(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        cleanSession(session);
        return "userHome";
    }

    @RequestMapping(value = "otherHome" ,method = RequestMethod.GET)
    public String otherHome(@RequestParam(value = "userId",required = false) int userId, Model model,HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        if (userId == user.getId()) {
            return "redirect:/user/userHome";
        }
        UserVo otherUser = userService.selectUserById(userId);
        model.addAttribute("otherUser", otherUser);
        List<ClubVo> clubs = new ArrayList<>();
        List<ActivityVo> activities = new ArrayList<>();
        for(Integer id : userService.getUserJoin(otherUser.getId())){
            clubs.add(clubService.getClubVo(id));
        }
        for(ClubVo club : clubs){
            if (club!=null){
                activities.addAll(activityService.getActivityVoByClubId(club.getId(),otherUser.getId()));
            }
        }
        session.setAttribute("activities", activities);
        return "otherHome";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String searchGet(HttpSession session,
                            @RequestParam(value = "search-text",required = false)String searchText,
                            @RequestParam(value = "search-target",required = false)String searchTarget,
                            @RequestParam(value = "pageNo1",required = false) String pageNo1,
                            @RequestParam(value = "pageNo2",required = false) String pageNo2){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        cleanSession(session);
        if (pageNo1 == null || pageNo1.isEmpty()){
            pageNo1 = "1";
        }
        if (pageNo2 == null || pageNo2.isEmpty()){
            pageNo2 = "1";
        }
        if(Objects.equals(searchText, "")||searchText.isEmpty()){
            return "index";
        }
        session.setAttribute("searchText", searchText);
        session.setAttribute("searchTarget", searchTarget);
        showSearch(session,Integer.parseInt(pageNo1),Integer.parseInt(pageNo2));
        return "search";
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String searchPost(HttpSession session,
                             @RequestParam(value = "search-text",required = false)String searchText,
                             @RequestParam(value = "search-target",required = false)String searchTarget,
                             @RequestParam(value = "pageNo1",required = false) String pageNo1,
                             @RequestParam(value = "pageNo2",required = false) String pageNo2){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        cleanSession(session);
        if (pageNo1 == null || pageNo1.isEmpty()){
            pageNo1 = "1";
        }
        if (pageNo2 == null || pageNo2.isEmpty()){
            pageNo2 = "1";
        }
        if(Objects.equals(searchText, "")||searchText.isEmpty()){
            return "redirect:/user/index";
        }
        session.setAttribute("searchText", searchText);
        session.setAttribute("searchTarget", searchTarget);
        showSearch(session,Integer.parseInt(pageNo1),Integer.parseInt(pageNo2));
        return "search";
    }

    @RequestMapping(value = "changeAvatar",method = RequestMethod.GET)
    public String changeAvatarGet(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        return "changeAvatar";
    }

    @RequestMapping(value = "changeAvatar",method = RequestMethod.POST)
    public String changeAvatarPost(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        return "changeAvatar";
    }

    @RequestMapping(value = "changeUser",method = RequestMethod.POST)
    public String changeUserPost(UserVo userVo,HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        userVo.setId(user.getId());
        userService.updateUser(userVo);
        session.setAttribute("user", userService.selectUserById(user.getId()));
        return "userHome";
    }

    @RequestMapping(value = "changeUser",method = RequestMethod.GET)
    public String changeUserGet(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        return "changeUser";
    }

    /**
     * 更换头像
     * @param newAvatar        头像图片
     * @param session       session
     * @return              成功返回到userHome，失败返回到userHome
     */
    @RequestMapping("/uploadAvatar")
    public String testUpload(@RequestParam("newAvatar") MultipartFile newAvatar,
                             HttpSession session) throws IOException {
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        if (newAvatar.isEmpty()) {
            session.setAttribute("error","修改失败");
            return "changeAvatar";
        }
        String fileName = newAvatar.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        String fileName2 = user.getUserName() + res + fileName;
        String finalPath = Constants.AVATAR_PATH2 + fileName2;
        // 验证服务器是否已经创建了该目录
        File fileSave = new File(Constants.AVATAR_PATH2);
        // 判断保存路径文件对象是否存在
        if (!fileSave.exists()) {
            // 创建该目录
            fileSave.mkdirs();
        }
        try{
            byte[] bytes = newAvatar.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);
//            用不了transferTo，不知道为什么
//            picture.transferTo(new File(finalPath));
        }catch (Exception e){
            e.printStackTrace();
        }
        user.setAvatarFname(fileName2);
        userService.updateUserAvatar(user.getId(), user.getAvatarFname());
        session.setAttribute("user",user);
        session.setAttribute("error","修改成功");
        return "userHome";
    }

    /**
     * 处理头像显示请求
     */
    @RequestMapping(value = "/showAvatar/{fileName}.{suffix}")
    public void showAvatar(@PathVariable("fileName") String fileName,
                           @PathVariable("suffix") String suffix,
                           HttpServletResponse response){
        File imgFile = new File(Constants.AVATAR_PATH + fileName + "." + suffix);
        responseFile(response,imgFile);
    }

    /**
     * 处理图片显示请求
     */
    @RequestMapping(value = "/showPic/{fileName}.{suffix}")
    public void showPic(@PathVariable("fileName") String fileName,
                        @PathVariable("suffix") String suffix,
                        HttpServletResponse response){
        File imgFile = new File(Constants.IMG_PATH + fileName + "." + suffix);
        responseFile(response,imgFile);
    }

    /**
     * 响应输出图片
     * @param response  请求
     * @param file      文件
     */
    private void responseFile(HttpServletResponse response,File file){
        try(InputStream inputStream = new FileInputStream(file);
            OutputStream os = response.getOutputStream()){
            byte[] buffer = new byte[1024];
            while(inputStream.read(buffer)!=-1){
                os.write(buffer);
            }
            os.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/userJoin")
    public String userJoin(HttpSession session,HttpServletRequest request){
        UserVo user = (UserVo) session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        clubService.userJoinClub(user.getId(),clubId);
        return "redirect:/club/clubHome?clubId=" + clubId;
    }

    @RequestMapping(value = "/userOut")
    public String userOut(HttpSession session,HttpServletRequest request){
        UserVo user = (UserVo) session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        clubService.userOutClub(user.getId(),clubId);
        return "redirect:/club/clubHome?clubId=" + clubId;
    }

    private void cleanSession(HttpSession session){
        session.removeAttribute("activities");
        session.removeAttribute("clubs");
        session.removeAttribute("manageClubs");
        session.removeAttribute("activity");
        session.removeAttribute("club");
        session.removeAttribute("tjClubs");
        session.removeAttribute("activitiesUnStart");
        session.removeAttribute("activitiesStarting");
        session.removeAttribute("activitiesEnd");
    }

    private void showSearch(HttpSession session,int pageNo1,int pageNo2){
        UserVo user = (UserVo)session.getAttribute("user");
        String searchText = (String) session.getAttribute("searchText");
        String searchTarget = (String) session.getAttribute("searchTarget");
        //俱乐部
        if(Objects.equals(searchTarget,"0")){
            cleanSession(session);
            if(!searchText.equals("")){
                int maxPage1 = clubService.getClubNumberByName(searchText) / PAGE_SIZE + 1;
                session.setAttribute("maxPage1", maxPage1);
                if (pageNo1 > maxPage1){
                    pageNo1 = maxPage1;
                }else if (pageNo1 <= 0){
                    pageNo1 = 1;
                }
                if(clubService.getClubNumberByName(searchText) > 0){
                    List<ClubVo> clubVos = clubService.getClubVoByName(searchText,pageNo1,PAGE_SIZE,user.getId());
                    session.setAttribute("clubs", clubVos);
                }
                session.setAttribute("pageNo1", pageNo1);
            }

        }
        //活动
        else if(Objects.equals(searchTarget,"1")){
            cleanSession(session);
            if(!searchText.equals("")){
                int maxPage2 = activityService.getActivityVoNumberByName(searchText) / PAGE_SIZE + 1;
                session.setAttribute("maxPage2", maxPage2);
                if (pageNo2 > maxPage2){
                    pageNo2 = maxPage2;
                }else if (pageNo2 <= 0){
                    pageNo2 = 1;
                }
                if (activityService.getActivityVoNumberByName(searchText) > 0){
                    List<ActivityVo> activityVos = activityService.getActivityVoByName(searchText,pageNo2,PAGE_SIZE,user.getId());
                    session.setAttribute("activities", activityVos);
                }
                session.setAttribute("pageNo2", pageNo2);
            }
        }
        //标签
        else if(Objects.equals(searchTarget,"2")){
            cleanSession(session);
            if(!searchText.equals("")){
                int maxPage1 = clubService.getClubNumberByTag(searchText) / PAGE_SIZE + 1;
                session.setAttribute("maxPage1", maxPage1);
                int maxPage2 = activityService.getActivityVoNumberByTag(searchText) / PAGE_SIZE + 1;
                session.setAttribute("maxPage2", maxPage2);
                if (pageNo1 > maxPage1){
                    pageNo1 = maxPage1;
                }else if (pageNo1 <= 0){
                    pageNo1 = 1;
                }
                if (pageNo2 > maxPage2){
                    pageNo2 = maxPage2;
                }else if (pageNo2 <= 0){
                    pageNo2 = 1;
                }
                if(clubService.getClubNumberByTag(searchText) > 0){
                    List<ClubVo> clubVos = clubService.getClubVoByTag(searchText,pageNo2,PAGE_SIZE,user.getId());
                    session.setAttribute("clubs", clubVos);
                }
                if(activityService.getActivityVoNumberByTag(searchText) > 0){
                    List<ActivityVo> activityVos = activityService.getActivityVoByTag(searchText,pageNo2,PAGE_SIZE,user.getId());
                    session.setAttribute("activities", activityVos);
                }
                session.setAttribute("pageNo1", pageNo1);
                session.setAttribute("pageNo2", pageNo2);
            }
        }
        //用户
        else if(Objects.equals(searchTarget,"3")){
            cleanSession(session);
            if(!searchText.equals("")){
                int maxPage2 = userService.getUserNumber(searchText) / PAGE_SIZE + 1;
                session.setAttribute("maxPage2",maxPage2);
                if(pageNo2 > maxPage2){
                    pageNo2 = maxPage2;
                }else if(pageNo2 <= 0){
                    pageNo2 = 1;
                }
                if(userService.getUserNumber(searchText) > 0){
                    List<UserVo> userVos = userService.getUser(searchText,pageNo2,PAGE_SIZE);
                    session.setAttribute("users",userVos);
                }
                session.setAttribute("pageNo3",pageNo2);
            }
        }
    }

    private void showActivity(HttpSession session ,int pageNo1,int pageNo2,int pageNo3){
        UserVo user = (UserVo)session.getAttribute("user");
        List<ActivityVo> activitiesUnStart = new ArrayList<>();
        List<ActivityVo> activitiesStarting = new ArrayList<>();
        List<ActivityVo> activitiesEnd = new ArrayList<>();
        //还没开始的
        int maxPageNo1 = activityService.getActivityUnStartNumberByUserId(user.getId()) / PAGE_SIZE + 1;
        //正在进行中的
        int maxPageNo2 = activityService.getActivityStartingNumberByUserId(user.getId()) / PAGE_SIZE + 1;
        //结束的
        int maxPageNo3 = activityService.getActivityEndNumberByUserId(user.getId()) / PAGE_SIZE + 1;
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
        if (activityService.getActivityUnStartNumberByUserId(user.getId()) != 0){
            activitiesUnStart = activityService.getActivityUnStartByUserId(user.getId(), pageNo1,PAGE_SIZE);
            session.setAttribute("activitiesUnStart", activitiesUnStart);
        }
        if (activityService.getActivityStartingNumberByUserId(user.getId()) != 0){
            activitiesStarting = activityService.getActivityStartingByUserId(user.getId(), pageNo2,PAGE_SIZE);
            session.setAttribute("activitiesStarting", activitiesStarting);
        }
        if (activityService.getActivityEndNumberByUserId(user.getId()) != 0){
            activitiesEnd = activityService.getActivityEndByUserId(user.getId(), pageNo3,PAGE_SIZE);
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
