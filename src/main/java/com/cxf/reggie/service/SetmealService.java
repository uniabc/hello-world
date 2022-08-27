package com.cxf.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxf.reggie.dto.SetmealDto;
import com.cxf.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author jack_chen
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2022-08-25 21:52:14
*/
public interface SetmealService extends IService<Setmeal> {
    Page<SetmealDto> page(Integer nowPage,Integer pageSize,String name);

    void saveWithSetmealDishes(SetmealDto setmealDto);

    SetmealDto getWithSetmealDishes(Long id);

    void updateWithSetMealDishes(SetmealDto setmealDto);

    void deleteWithSetMealDishes(List<Long> ids);
}
