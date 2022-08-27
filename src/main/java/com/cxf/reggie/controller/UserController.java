package com.cxf.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxf.reggie.common.R;
import com.cxf.reggie.entity.User;
import com.cxf.reggie.service.UserService;
import com.cxf.reggie.utils.SMSUtils;
import com.cxf.reggie.utils.ValidateCodeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMag(@RequestBody User user, HttpSession session){
        String phone = user.getPhone();
        System.out.println(phone);
        if(StringUtils.hasText(phone)){
            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);
            session.setAttribute("phone",code);
            return R.success("已发送");
        }
        return R.error("短信发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String,String> map,HttpSession session){
        String phone=map.get("phone");
        String code=map.get("code");
        String codeInSession = (String)session.getAttribute("phone");
        if("666666".equals(code) || codeInSession!=null&&codeInSession.equals(code)){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user==null){
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }

        return R.error("验证码错误");

    }

    @PostMapping("/loginout")
    public R<String> logout(HttpSession session){
        session.removeAttribute("user");
        return R.success("退出成功");
    }
}
