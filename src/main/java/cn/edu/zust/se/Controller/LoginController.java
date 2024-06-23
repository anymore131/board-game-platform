package cn.edu.zust.se.Controller;

import cn.edu.zust.se.Service.UserServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
