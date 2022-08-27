package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.entity.DishFlavor;
import com.cxf.reggie.service.DishFlavorService;
import com.cxf.reggie.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author jack_chen
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2022-08-25 16:36:44
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




