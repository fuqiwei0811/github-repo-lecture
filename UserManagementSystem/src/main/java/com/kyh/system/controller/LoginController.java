package com.kyh.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kyh.system.entity.User;
import com.kyh.system.service.UserService;

@Controller
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {
        return "/login/login";
    }

    /***
     * これはModelAndViewを使用してデータを返す形式です。Mapと同様にデータを返しますが、呼び出し方法が異なります。
     * */
    @RequestMapping(value = "/userLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        User user = new User();
        User loginUser = new User();
        user.setPassword(password);
        user.setUserId(Integer.parseInt(id));
        loginUser = userService.getUserById(user);
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            model.setViewName("login/index");
        } else {
            model.addObject("MSG", "ユーザー名またはパスワードが間違っています");
            model.setViewName("/login/login");
        }
        return model;
    }

    /**
     * ログイン成功後、歓迎ページを読み込み、String型でページのパスと名前を返します
     * */
    @RequestMapping(value = "welcome", method = {RequestMethod.POST, RequestMethod.GET})
    public String welcome() {
        return "/login/welcome";
    }
}
