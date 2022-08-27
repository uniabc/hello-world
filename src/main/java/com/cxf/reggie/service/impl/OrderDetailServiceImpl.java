package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.entity.OrderDetail;
import com.cxf.reggie.service.OrderDetailService;
import com.cxf.reggie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author jack_chen
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-08-26 15:51:49
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




