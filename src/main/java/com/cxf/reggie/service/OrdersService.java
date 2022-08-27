package com.cxf.reggie.service;

import com.cxf.reggie.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jack_chen
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-08-26 15:51:49
*/
public interface OrdersService extends IService<Orders> {

    void submit(Orders orders);
}
