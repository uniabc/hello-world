package com.cxf.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cxf.reggie.entity.Employee;
import com.cxf.reggie.entity.ShoppingCart;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 元数据对象处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

            metaObject.setValue("createTime", LocalDateTime.now());
            metaObject.setValue("updateTime",LocalDateTime.now());
            metaObject.setValue("createUser",BaseContext.getCurrentUserId());
            metaObject.setValue("updateUser",BaseContext.getCurrentUserId());


    }

    @Override
    public void updateFill(MetaObject metaObject) {


            metaObject.setValue("updateTime",LocalDateTime.now());
            metaObject.setValue("updateUser",BaseContext.getCurrentUserId());

    }
}
