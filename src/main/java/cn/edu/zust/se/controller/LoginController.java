package cn.edu.zust.se.controller;

import cn.edu.zust.se.bo.UserBo;
import cn.edu.zust.se.service.LoginServiceI;
import cn.edu.zust.se.vo.UserVo;
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
    LoginServiceI loginService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginGet(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginPost(UserBo userBo, HttpSession session) {
        if (userBo.getUserName().isEmpty() || userBo.getPassword().isEmpty()) {
            session.setAttribute("error", "未输入完整");
            return "login";
        }
        UserVo userVo = loginService.login(userBo);
        if (userVo != null) {
            session.setAttribute("user", userVo);
            return "redirect:/user/index";
        }
        session.setAttribute("error", "账号密码错误！");
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerGet(HttpSession session) {
        session.invalidate();
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerPost(UserBo userBo, HttpSession session) {
        if (userBo.getUserName().isEmpty() || userBo.getPassword().isEmpty() || userBo.getName().isEmpty()) {
            session.setAttribute("error", "未输入完整");
            return "register";
        }
        UserVo userVo = loginService.register(userBo);
        if (userVo != null) {
            session.setAttribute("user", userVo);
            return "redirect:/user/index";
        }
        session.setAttribute("error", "已存在该用户");
        return "register";
    }
}
