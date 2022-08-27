package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.common.CustomExeception;
import com.cxf.reggie.dto.SetmealDto;
import com.cxf.reggie.entity.Category;
import com.cxf.reggie.entity.Setmeal;
import com.cxf.reggie.entity.SetmealDish;
import com.cxf.reggie.service.CategoryService;
import com.cxf.reggie.service.SetmealDishService;
import com.cxf.reggie.service.SetmealService;
import com.cxf.reggie.mapper.SetmealMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author jack_chen
* @description 针对表【setmeal(套餐)】的数据库操作Service实现
* @createDate 2022-08-25 21:52:14
*/
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService{

    @Resource
    private CategoryService categoryService;

    @Resource
    private SetmealDishService setmealDishService;

    @Override
    public Page<SetmealDto> page(Integer nowPage, Integer pageSize, String name) {
        Page<Setmeal> setmealPage = new Page<>(nowPage,pageSize);
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.like(StringUtils.hasText(name),Setmeal::getName,name);
        setmealLambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        this.page(setmealPage,setmealLambdaQueryWrapper);

        Page<SetmealDto> setmealDtoPage = new Page<>(nowPage, pageSize);
        BeanUtils.copyProperties(setmealPage,setmealDtoPage,"records");
        ArrayList<SetmealDto> list = new ArrayList<>();


        for (Setmeal record : setmealPage.getRecords()) {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(record,setmealDto);
            Category category = categoryService.getById(record.getCategoryId());
            setmealDto.setCategoryName(category.getName());
            list.add(setmealDto);
        }
        setmealDtoPage.setRecords(list);

        return setmealDtoPage;
    }

    @Transactional
    @Override
    public void saveWithSetmealDishes(SetmealDto setmealDto) {
        this.save(setmealDto);
        Long setmealId=setmealDto.getId();
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealId);
        }
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public SetmealDto getWithSetmealDishes(Long id) {
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);

        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId,id);
        List<SetmealDish> setmealDishes = setmealDishService.list(setmealDishLambdaQueryWrapper);
        setmealDto.setSetmealDishes(setmealDishes);
        return setmealDto;
    }

    @Override
    public void updateWithSetMealDishes(SetmealDto setmealDto) {
        this.updateById(setmealDto);
        Long setmealId=setmealDto.getId();

        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmealId);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealId);
        }

        setmealDishService.saveBatch(setmealDishes);
    }

    @Transactional
    @Override
    public void deleteWithSetMealDishes(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper
                = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId,ids);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(setmealLambdaQueryWrapper);
        if(count>0){
            throw new CustomExeception("所选套餐中存在正在售卖中，不能删除");
        }
        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);

    }
}




