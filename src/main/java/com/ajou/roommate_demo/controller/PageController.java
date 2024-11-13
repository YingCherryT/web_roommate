package com.ajou.roommate_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.controller
 * @CLASS_NAME: PageController
 * @USER: BTS&ARMY
 * @Date 2024/11/12 15:39
 * @Version 1.0
 */
@Controller
@RequestMapping("/for")  // 可以为所有页面跳转请求统一设置一个前缀
public class PageController {

    // 跳转到登录页面
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // 返回 login.jsp 页面
    }

    // 跳转到首页
    @GetMapping("/index")
    public String showHomePage() {
        return "index";  // 返回 home.jsp 页面
    }

    // 错误页面
    @GetMapping("/error")
    public String showErrorPage() {
        return "error";  // 返回 error.jsp 页面
    }
}
