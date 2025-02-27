package com.example.demo02.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 使用户登出，清除会话
        return "redirect:/index"; // 重定向到index页面
    }
}