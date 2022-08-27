package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.entity.AddressBook;
import com.cxf.reggie.service.AddressBookService;
import com.cxf.reggie.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author jack_chen
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2022-08-26 15:51:49
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




