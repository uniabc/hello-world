package com.cxf.reggie.controller;

import com.cxf.reggie.common.R;

import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@RestController
@RequestMapping("/car")
public class CarController {
    @RequestMapping("/test1")
    public R get(@RequestBody Person person){
        System.out.println(person);

        return R.success(person);
    }
    @RequestMapping("/test2")
    public R get2(Person person){
        System.out.println(person);
        return R.success(person);
    }


}

class Car{
    String name;
    Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
@Data
class Person{
    String name;
    Car car;
}


class CarConverter implements Converter<String, Car> {
    @Override
    public Car convert(String source) {
        if(StringUtils.hasText(source)){
            try {
                String[] split = source.split(",");
                Car car = new Car();
                car.setName(split[0]);
                car.setPrice(Double.parseDouble(split[1]));
                return car;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

}

@Component
class WebMvcConfigurerImpl implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CarConverter());
    }
}