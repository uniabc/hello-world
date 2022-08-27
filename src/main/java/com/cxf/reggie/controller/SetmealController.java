package com.cxf.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxf.reggie.common.R;
import com.cxf.reggie.dto.SetmealDto;
import com.cxf.reggie.entity.Setmeal;
import com.cxf.reggie.service.SetmealService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Resource
    private SetmealService setmealService;

    @GetMapping("/page")
    public R<Page<SetmealDto>> page(@RequestParam("page") Integer nowPage,
                        @RequestParam("pageSize") Integer pageSize,
                        @RequestParam(value = "name",required = false) String name ){
        Page<SetmealDto> page = setmealService.page(nowPage, pageSize, name);
        return R.success(page);
    }

    @PostMapping
    public R<String> saveWithSetmealDishes(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithSetmealDishes(setmealDto);
        return R.success("已添加");
    }

    @GetMapping("{id}")
    public R<SetmealDto> getWithSetmealDishes(@PathVariable("id") Long id){
        SetmealDto setmealDto=setmealService.getWithSetmealDishes(id);
        return R.success(setmealDto);
    }

    @PutMapping
    public R<String> updateWithSetMealDishes(@RequestBody SetmealDto setmealDto){
        setmealService.updateWithSetMealDishes(setmealDto);
        return R.success("已更新信息");
    }

    @DeleteMapping()
    public R<String> deleteWithSetMealDishes(@RequestParam("ids") List<Long> ids){
        setmealService.deleteWithSetMealDishes(ids);
        return R.success("成功删除");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> list(@RequestParam("categoryId") Long categoryId,Integer status){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(categoryId!=null,Setmeal::getCategoryId,categoryId);
        queryWrapper.eq(status!=null,Setmeal::getStatus,status);
        List<Setmeal> list = setmealService.list(queryWrapper);
        return R.success(list);

    }

}
