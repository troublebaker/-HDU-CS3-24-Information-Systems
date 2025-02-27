package com.example.demo02.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo02.domain.Courses;
import com.example.demo02.service.CoursesService;
import com.example.demo02.mapper.CoursesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 17471
* @description 针对表【courses】的数据库操作Service实现
* @createDate 2025-02-26 10:18:18
*/
@Service
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, Courses>
    implements CoursesService{


}




