package cn.edu.zust.se.controller;

import cn.edu.zust.se.service.ActivityServiceI;
import cn.edu.zust.se.service.ClubServiceI;
import cn.edu.zust.se.service.UserServiceI;
import cn.edu.zust.se.util.Constants;
import cn.edu.zust.se.vo.ActivityVo;
import cn.edu.zust.se.vo.ClubVo;
import cn.edu.zust.se.vo.UserVo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public String indexGet(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        List<Integer> userJoinId = userService.getUserJoin(user.getId());
        List<ActivityVo> activities = new ArrayList<>();
        List<ClubVo> clubs = new ArrayList<>();
        for(Integer id : userJoinId){
            clubs.add(clubService.getClubVo(id));
        }
        for(ClubVo club : clubs){
            activities.addAll(activityService.getActivityVoById(club.getId(),user.getId()));
        }
        session.setAttribute("activities", activities);
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
    public String userHomeGet(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
        List<ClubVo> clubs = clubService.getClubVoByUserJoin(user.getId());
        session.setAttribute("clubs", clubs);
        List<ClubVo> manageClubs = clubService.getClubVoManageByUserJoin(user.getId());
        session.setAttribute("manageClubs", manageClubs);
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
        if(searchText == null){
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
        if(searchText == null){
            return "index";
        }
        session.setAttribute("searchText", searchText);
        session.setAttribute("searchTarget", searchTarget);
        showSearch(session,Integer.parseInt(pageNo1),Integer.parseInt(pageNo2));
        return "search";
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

    private void cleanSession(HttpSession session){
        session.removeAttribute("activities");
        session.removeAttribute("clubs");
        session.removeAttribute("manageClubs");
    }

    private void showSearch(HttpSession session,int pageNo1,int pageNo2){
        UserVo user = (UserVo)session.getAttribute("user");
        int pageSize = 16;
        String searchText = (String) session.getAttribute("searchText");
        String searchTarget = (String) session.getAttribute("searchTarget");
        //俱乐部
        if(Objects.equals(searchTarget,"0")){
            cleanSession(session);
            int maxPage1 = clubService.getClubNumberByName(searchText) / pageSize + 1;
            session.setAttribute("maxPage1", maxPage1);
            if (pageNo1 > maxPage1){
                pageNo1 = maxPage1;
            }else if (pageNo1 <= 0){
                pageNo1 = 1;
            }
            if(clubService.getClubNumberByName(searchText) > 0){
                List<ClubVo> clubVos = clubService.getClubVoByName(searchText,pageNo1,pageSize,user.getId());
                session.setAttribute("clubs", clubVos);
            }
            session.setAttribute("pageNo1", pageNo1);
        }
        //标签
        else if(Objects.equals(searchTarget,"2")){
            cleanSession(session);
            int maxPage1 = clubService.getClubNumberByTag(searchText) / pageSize + 1;
            session.setAttribute("maxPage1", maxPage1);
            int maxPage2 = activityService.getActivityVoNumberByTag(searchText) / pageSize + 1;
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
                List<ClubVo> clubVos = clubService.getClubVoByTag(searchText,pageNo2,pageSize,user.getId());
                session.setAttribute("clubs", clubVos);
            }
            if(activityService.getActivityVoNumberByTag(searchText) > 0){
                List<ActivityVo> activityVos = activityService.getActivityVoByTag(searchText,pageNo2,pageSize,user.getId());
                session.setAttribute("activities", activityVos);
            }
            session.setAttribute("pageNo1", pageNo1);
            session.setAttribute("pageNo2", pageNo2);
        }
        //活动
        else if(Objects.equals(searchTarget,"1")){
            cleanSession(session);
            int maxPage2 = activityService.getActivityVoNumberByName(searchText) / pageSize + 1;
            session.setAttribute("maxPage2", maxPage2);
            if (pageNo2 > maxPage2){
                pageNo2 = maxPage2;
            }else if (pageNo2 <= 0){
                pageNo2 = 1;
            }
            if (activityService.getActivityVoNumberByName(searchText) > 0){
                List<ActivityVo> activityVos = activityService.getActivityVoByName(searchText,pageNo2,pageSize,user.getId());
                session.setAttribute("activities", activityVos);
            }
            session.setAttribute("pageNo2", pageNo2);
        }
    }
}
