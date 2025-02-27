package com.example.demo02.controller;

import com.example.demo02.domain.Courses;
import com.example.demo02.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private CoursesService coursesService; // 假设你有一个 CoursesService

    @GetMapping
    public String listCourses(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size) {
        List<Courses> allCourses = coursesService.list(); // 获取所有课程
        int totalCourses = allCourses.size();
        int totalPages = (int) Math.ceil((double) totalCourses / size);

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
        List<Courses> courses = allCourses.subList(startIndex, endIndex);

        model.addAttribute("courses", courses);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "teacher"; // 返回teacher.html模板
    }

    @GetMapping("/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Courses()); // 创建一个新的课程对象
        return "add_course"; // 返回添加课程的表单页面
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Courses course) {
        coursesService.save(course); // 使用 MyBatis-Plus 保存课程
        return "redirect:/teacher"; // 重定向到课程列表
    }

    @GetMapping("/edit")
    public String editCourseForm(@RequestParam Long id, Model model) {
        Courses course = coursesService.getById(id); // 根据 ID 获取课程
        model.addAttribute("course", course);
        return "edit_course"; // 返回编辑课程的表单页面
    }

    @PostMapping("/edit")
    public String editCourse(@ModelAttribute Courses course) {
        coursesService.updateById(course); // 使用 MyBatis-Plus 更新课程
        return "redirect:/teacher"; // 重定向到课程列表
    }

    @GetMapping("/delete")
    public String deleteCourse(@RequestParam Long id) {
        coursesService.removeById(id); // 使用 MyBatis-Plus 删除课程
        return "redirect:/teacher"; // 重定向到课程列表
    }
}