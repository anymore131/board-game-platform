package cn.edu.zust.se.controller;

import cn.edu.zust.se.service.UserServiceI;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Resource
    UserServiceI userService;

    @RequestMapping("hi")
    public String login(){
        return "hi";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String loginGet(HttpSession session){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String loginPost(HttpSession session){
        return "login";
    }

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String registerGet(HttpSession session){
        return "login";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String registerPost(HttpSession session){
        return "login";
    }
}
