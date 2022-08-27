package com.cxf.reggie.service;

import com.cxf.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jack_chen
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-08-25 11:40:41
*/
public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
