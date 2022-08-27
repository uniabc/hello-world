package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.entity.ShoppingCart;
import com.cxf.reggie.service.ShoppingCartService;
import com.cxf.reggie.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author jack_chen
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-08-26 15:51:49
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




