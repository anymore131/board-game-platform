package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.ActivityBo;
import cn.edu.zust.se.bo.ClubBo;
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

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            activities.addAll(activityService.getActivityVoById(club.getId()));
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
        return "index";
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
}
