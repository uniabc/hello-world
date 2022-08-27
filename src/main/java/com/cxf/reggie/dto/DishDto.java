package com.cxf.reggie.dto;

import com.cxf.reggie.entity.Dish;
import com.cxf.reggie.entity.DishFlavor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors=new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
