package com.example.demo02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo02.domain.Users;
import com.example.demo02.service.UsersService;
import com.example.demo02.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 17471
* @description 针对表【users】的数据库操作Service实现
* @createDate 2025-02-26 11:36:06
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




