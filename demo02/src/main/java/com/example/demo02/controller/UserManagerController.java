package com.example.demo02.controller;

import com.example.demo02.domain.Users;
import com.example.demo02.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class UserManagerController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public String listUsers(Model model,
                            @RequestParam(defaultValue = "1") int page) {
        int size = 10;
        List<Users> allUsers = usersService.list();
        int totalUsers = allUsers.size();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalUsers);
        List<Users> users = allUsers.subList(startIndex, endIndex);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "manager";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new Users()); // 创建一个新的用户对象
        return "add_user"; // 返回添加用户的视图
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute Users user) {
        usersService.save(user); // 保存用户
        return "redirect:/manager"; // 重定向到用户列表
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam Integer id, Model model) {
        Users user = usersService.getById(id);
        model.addAttribute("user", user); // 获取用户并添加到模型中
        return "edit_user"; // 返回修改用户的视图
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute Users user) {
        usersService.updateById(user); // 更新用户信息
        return "redirect:/manager"; // 重定向到用户列表
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Integer id) {
        usersService.removeById(id);
        return "redirect:/manager"; // 重定向到用户列表
    }
}