package com.kyh.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kyh.system.entity.User;
import com.kyh.system.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    static final int pageSize = 10;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(HttpServletRequest request, HttpServletResponse response) {
        String pageNum = request.getParameter("pageNum");
        int pageNumber = 0;
        if (null == pageNum) {
            pageNumber = 1;
        }

        return userService.findAllUser(pageNumber, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteUser(@PathVariable("key") int key) {
        return userService.delete(key);
    }

    // ユーザー情報の更新
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(User user) {
        return userService.update(user);
    }

    // ユーザー情報管理
    @RequestMapping(value = "/userinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfomation(HttpSession session) {
        return "/common/information";
    }

    /*
       ユーザー情報リスト
    */
    @PostMapping(value = "/userinforlist")
    @ResponseBody
    public Map userinforlist(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("rows")); // pageSize
        int startRecord = (page - 1) * pageSize + 1;
        int total = userService.getUsernumber();
        List<User> userinforlist = userService.findAllUser(startRecord, pageSize);
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", userinforlist);
        return resultMap;
    }

    /*
    ユーザー情報画面に遷移
    */
    @GetMapping(value = "/info")
    public String stuinfor() {
        return "/common/information";
    }

    @PostMapping(value = "/update") // 新規ユーザーと変更されたユーザー情報を保存
    @ResponseBody
    public Map<String, String> update(@RequestParam("userId") String userId,
                                      @RequestParam("userName") String userName,
                                      @RequestParam("password") String password,
                                      @RequestParam("phone") String phone, HttpSession session) {

        Map<String, String> map = new HashMap<>();
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setPassword(password);
        user.setUserName(userName);
        user.setPhone(phone);
        try {
            userService.update(user);
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "情報を確認し、ログイン名が一意であることを確認してください");
        }
        return map;
    }

    @RequestMapping(value = "/updateMyInfo", method = {RequestMethod.POST, RequestMethod.GET}) // 新規ユーザーと変更されたユーザー情報を保存
    @ResponseBody
    public ModelAndView updateMyInfo(@RequestParam("userId") String userId,
                                     @RequestParam("userName") String userName,
                                     @RequestParam("password") String password,
                                     @RequestParam("phone") String phone, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setPassword(password);
        user.setUserName(userName);
        user.setPhone(phone);
        try {
            userService.update(user);
            model.addObject("message", "変更成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("message", "変更失敗");
        model.setViewName("/common/error");
        return model;
    }

    @PostMapping(value = "/save_users") // 新規ユーザーと変更されたユーザー情報を保存
    @ResponseBody
    public Map<String, String> saveUsers(@RequestParam("userName") String userName,
                                         @RequestParam("password") String password,
                                         @RequestParam("phone") String phone, HttpSession session) {

        Map<String, String> map = new HashMap<>();
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        user.setPhone(phone);
        try {
            userService.addUser(user);
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "情報を確認し、ログイン名が一意であることを確認してください");
        }
        return map;
    }

    /***
     * ユーザー削除 マップ形式で返す
     *
     */
    @PostMapping(value = "/remove_users") // ユーザー削除
    @ResponseBody
    public Map<String, String> removeUsers(@RequestParam("id") Integer id, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        if (((User) session.getAttribute("user")).getUserId().equals(id)) {
            result.put("msg", "不正な操作！自分を削除することはできません！");
            return result;
        }
        try {
            userService.delete(id);
            result.put("success", "true");
            System.out.println("削除Id: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "エラーが発生しました。");
        }
        return result;
    }

    /***
     * 個人情報の変更
     */
    @RequestMapping(value = "myInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView myInfo(HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Integer id = user.getUserId();
        user.setUserId(id);
        User logUser = userService.getUserById(user);
        session.setAttribute("user", logUser);
        model.addObject("user", logUser);
        model.setViewName("/common/myInfo");
        return model;
    }

    /***
     * パスワード変更
     */
    @RequestMapping(value = "/modifypassword", method = {RequestMethod.POST, RequestMethod.GET}) // 新規ユーザーと変更されたユーザー情報を保存
    @ResponseBody
    public ModelAndView modifypassword(@RequestParam("userId") String userId,
                                       @RequestParam("oldpassword") String oldpassword,
                                       @RequestParam("newpassword1") String newpassword1,
                                       @RequestParam("newpassword2") String newpassword2, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User Loginuser = (User) session.getAttribute("user");
        if (oldpassword == null || "".equals(oldpassword)) {
            model.addObject("message", "初期パスワードは空にできません");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword1 == null || "".equals(newpassword1)) {
            model.addObject("message", "新しいパスワードは空にできません");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword2 == null || "".equals(newpassword2)) {
            model.addObject("message", "確認用パスワードは空にできません");
            model.setViewName("/common/success");
            return model;
        } else if (!newpassword2.equals(newpassword1)) {
            model.addObject("message", "パスワードが一致しません");
            model.setViewName("/common/success");
            return model;
        } else if (!Loginuser.getPassword().equals(oldpassword)) {
            model.addObject("message", "初期パスワードが正しくありません");
            model.setViewName("/common/success");
            return model;
        }

        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setPassword(newpassword2);
        try {
            userService.update(user);
            model.addObject("message", "変更成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("message", "変更失敗");
        model.setViewName("/common/success");
        return model;
    }

    @RequestMapping(value = "/exit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView exit(HttpSession session) {
        session.removeAttribute("user");
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/login/");
        return model;
    }

}
