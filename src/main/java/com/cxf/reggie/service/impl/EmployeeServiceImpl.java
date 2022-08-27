package com.cxf.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxf.reggie.entity.Employee;
import com.cxf.reggie.mapper.EmployeeMapper;
import com.cxf.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends
        ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
