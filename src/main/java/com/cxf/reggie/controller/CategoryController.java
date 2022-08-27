package com.cxf.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxf.reggie.common.R;
import com.cxf.reggie.entity.Category;
import com.cxf.reggie.service.CategoryService;
import com.cxf.reggie.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.crypto.spec.PSource;
import java.util.List;

@RestController()
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;


    @GetMapping("/page")
    public R<Page<Category>> listByPage(@RequestParam("page") Integer nowPage,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam(value = "name",required = false) String name){
        Page<Category> categoryPage = new Page<>(nowPage, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name),Category::getName,name);
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(categoryPage,queryWrapper);
        return R.success(categoryPage);
    }

    @DeleteMapping
    public R<String> deleteById(Long id){
        categoryService.remove(id);
        return R.success("操作成功");

    }

    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Integer type){
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(type!=null,Category::getType,type);
        List<Category> list = categoryService.list(categoryLambdaQueryWrapper);

        return R.success(list);
    }



}
