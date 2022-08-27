package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.entity.User;
import com.cxf.reggie.service.UserService;
import com.cxf.reggie.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author jack_chen
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2022-08-26 11:52:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




