package com.example.demo02.controller;
import com.example.demo02.domain.Users;
import com.example.demo02.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsersService userService; // 注入UserService

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // 返回登录页面
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(@RequestParam Integer id,
                        @RequestParam String name,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        // 根据ID查询用户
        Users user = userService.getById(id);

        // 验证用户信息
        if (user != null && user.getName().equals(name) && user.getPassword().equals(password)) {
            // 登录成功，保存用户信息到session
            session.setAttribute("user", user);
            return "redirect:/index"; // 登录成功后重定向到index页面
        } else {
            // 登录失败，添加错误信息并重定向回登录页面
            model.addAttribute("error", "登录失败，用户名或密码错误");
            return "login"; // 返回登录页面
        }
    }
}