package com.cxf.reggie.service;

import com.cxf.reggie.dto.DishDto;
import com.cxf.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author jack_chen
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-08-25 14:10:24
*/
public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getWithFlavor(Long id);

    public List<DishDto> getWithFlavorByCategoryId(Long categoryId);

    void updateWithFlavor(DishDto dishDto);
}
