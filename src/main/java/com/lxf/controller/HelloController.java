package com.lxf.controller;

import com.lxf.common.s9010.S9010ManagerDBO;
import com.lxf.common.s9010.S9010ManagerDao;
import com.lxf.common.s9010.S9010ManagerService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiXiaoFeng
 * @date 2022年01月07日 14:30
 */
@Controller
public class HelloController {

    @Autowired
    S9010ManagerService s9010ManagerService;
    @Autowired
    S9010ManagerDao s9010ManagerDao;

    @RequestMapping(value = "/user/login", params = {"account","password"}, method = RequestMethod.POST)
    public String userLogin(@RequestParam("account") String account, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        String digest = DigestUtils.md5DigestAsHex(password.getBytes());
        S9010ManagerDBO s9010ManagerDBO = new S9010ManagerDBO();
        s9010ManagerDBO.setPassword(digest);
        s9010ManagerDBO.setUserName(account);
        List<S9010ManagerDBO> s9010List = s9010ManagerService.doSelectList(s9010ManagerDBO);
        if (s9010List.size() < 1) {
            model.addAttribute("msg","用户名或者密码错误");
            return "login";
        }
        model.addAttribute("user",s9010List.get(0));
        request.getSession().setAttribute("userAccount", account);
        return "index";
    }


    @RequestMapping(value = "/errorPage")
    public String errorPage(Model model) {
        model.addAttribute("msg","禁止直接访问网页");
        return "error";
    }

    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession httpSession) {
        Object userAccount = httpSession.getAttribute("userAccount");
        if (userAccount == null || userAccount == "") {
            model.addAttribute("msg","当前用户已经失效，请重新登陆");
            return "login";
        }
        S9010ManagerDBO s9010ManagerDBO = new S9010ManagerDBO();
        s9010ManagerDBO.setUserName(userAccount.toString());
        List<S9010ManagerDBO> s9010List = s9010ManagerService.doSelectList(s9010ManagerDBO);
        if (s9010List.size() < 1) {
            model.addAttribute("msg","当前用户已经失效，请重新登陆");
            return "login";
        }
        model.addAttribute("user",s9010List.get(0));
        return "index";
    }

    @RequestMapping(value = "/docs")
    public String docs(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "docs";
    }

    @RequestMapping(value = "/setting")
    public String setting(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "settings";
    }

    @RequestMapping(value = "/404")
    public String failPage(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "404";
    }

    @RequestMapping(value = "/orders")
    public String orders(Model model) {
        S9010ManagerDBO s9010ManagerDBO = new S9010ManagerDBO();
        s9010ManagerDBO.setDelFlag("0");
        List<S9010ManagerDBO> s9010List = s9010ManagerService.doSelectList(s9010ManagerDBO);
        model.addAttribute("users",s9010List);
       // model.addAttribute("msg","禁止直接访问网页");
        return "orders";
    }

    @RequestMapping(value = "/signup")
    public String signup(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "signup";
    }

    @RequestMapping(value = "/account")
    public String account(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "account";
    }

    @RequestMapping(value = "/charts")
    public String charts(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "charts";
    }

    @RequestMapping(value = "/resetPassword")
    public String resetPassword(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "reset-password";
    }

    @RequestMapping(value = "/notifications")
    public String notifications(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "notifications";
    }

    @RequestMapping(value = "/help")
    public String help(Model model) {
       // model.addAttribute("msg","禁止直接访问网页");
        return "help";
    }

    @RequestMapping(value = "/")
    public String test() {
        return "login";
    }
}
