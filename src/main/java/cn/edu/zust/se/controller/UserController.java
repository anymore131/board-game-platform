package cn.edu.zust.se.controller;

import cn.edu.zust.se.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String indexGet(HttpSession session){
        UserVo user = (UserVo)session.getAttribute("user");
        if(user == null){
            return "redirect:/login/login";
        }
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
}
