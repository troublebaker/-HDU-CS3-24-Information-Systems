package com.example.demo02.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo02.domain.Courses;
import com.example.demo02.domain.Users;
import com.example.demo02.service.CoursesService;
import com.example.demo02.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CoursesService coursesService;
    @Autowired
    private UsersService usersService;
    @GetMapping(value = {"/index", "/"})

    public String showIndex(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user); // 将用户信息添加到模型
        return "index"; // 返回index页面
    }

    @GetMapping("/student")
    public String student(Model model,
                          @RequestParam(defaultValue = "1") int page, // 当前页，默认为1
                          @RequestParam(defaultValue = "10") int size) { // 每页显示的记录数，默认为10
        // 获取所有课程
        List<Courses> allCourses = coursesService.list(); // 获取所有课程
        int totalCourses = allCourses.size(); // 总课程数
        int totalPages = (int) Math.ceil((double) totalCourses / size); // 计算总页数

        // 确保当前页在有效范围内
        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        // 计算起始和结束索引
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalCourses);

        // 获取当前页的课程列表
        List<Courses> courses = allCourses.subList(startIndex, endIndex); // 获取当前页的课程

        model.addAttribute("courses", courses); // 将课程列表添加到模型中
        model.addAttribute("currentPage", page); // 当前页
        model.addAttribute("totalPages", totalPages); // 总页数
        return "student"; // 返回students.html模板
    }







    // 显示注册页面
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("users", new Users()); // 创建一个新的User对象
        return "register"; // 返回注册页面
    }

    // 处理注册请求
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users users) {
        // 调用服务层方法保存用户
        usersService.save(users);
        return "redirect:/login"; // 注册成功后重定向到登录页面
    }
}
