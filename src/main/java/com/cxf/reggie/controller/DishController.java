package com.cxf.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxf.reggie.common.R;
import com.cxf.reggie.dto.DishDto;
import com.cxf.reggie.entity.Category;
import com.cxf.reggie.entity.Dish;
import com.cxf.reggie.entity.DishFlavor;
import com.cxf.reggie.entity.Employee;
import com.cxf.reggie.service.CategoryService;
import com.cxf.reggie.service.DishFlavorService;
import com.cxf.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Resource
    private DishService dishService;

    @Resource
    private CategoryService categoryService;



    @GetMapping("/page")
    public R<Page<DishDto>> listByPage(@RequestParam("page") Integer nowPage,
                                    @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam(value = "name",required = false) String name){
        Page<Dish> page = new Page<>(nowPage,pageSize);
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(StringUtils.hasText(name),Dish::getName,name);
        dishLambdaQueryWrapper.orderByAsc(Dish::getSort);
        dishLambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(page,dishLambdaQueryWrapper);

        List<Dish> records = page.getRecords();
        List<DishDto> list=new ArrayList<>();
        Page<DishDto> dishDtoPage = new Page<>(nowPage,pageSize);
        BeanUtils.copyProperties(page,dishDtoPage,"records");
        for (Dish record : records) {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(record,dishDto);
            Category category = categoryService.getById(record.getCategoryId());
            dishDto.setCategoryName(category.getName());
            list.add(dishDto);
        }
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        return R.success("已添加");
    }

    @GetMapping("{id}")
    public R<DishDto> getDishDtoById(@PathVariable("id") Long id){
        DishDto dishDto = dishService.getWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("已更新");
    }

    //@GetMapping("/list")
    // public R<List<Dish>> list(Long categoryId){
    //     LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
    //     dishLambdaQueryWrapper.eq(categoryId!=null,Dish::getCategoryId,categoryId);
    //     List<Dish> list = dishService.list(dishLambdaQueryWrapper);
    //     return R.success(list);
    //
    // }

    @GetMapping("/list")
    public R<List<DishDto>> list(Long categoryId){
        List<DishDto> dishDtos = dishService.getWithFlavorByCategoryId(categoryId);
        return R.success(dishDtos);
    }
}
