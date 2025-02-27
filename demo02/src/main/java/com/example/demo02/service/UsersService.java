package com.example.demo02.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo02.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo02.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author 17471
* @description 针对表【users】的数据库操作Service
* @createDate 2025-02-26 11:36:06
*/
public interface UsersService extends IService<Users> {



}
