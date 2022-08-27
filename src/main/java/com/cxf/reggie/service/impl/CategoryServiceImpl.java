package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.common.CustomExeception;
import com.cxf.reggie.entity.Category;
import com.cxf.reggie.entity.Dish;
import com.cxf.reggie.entity.Setmeal;
import com.cxf.reggie.service.CategoryService;
import com.cxf.reggie.mapper.CategoryMapper;
import com.cxf.reggie.service.DishService;
import com.cxf.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author jack_chen
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-08-25 11:40:41
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Resource
    private DishService dishService;

    @Resource
    private SetmealService setmealService;
    /**
     * 根据id删除分类，删除之前需要进行判断是否有菜品、套餐关联
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if(count1>0){
            throw new CustomExeception("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if(count2>0){
            throw new CustomExeception("当前分类下关联了套餐，不能删除");
        }
        super.removeById(id);

    }
}




