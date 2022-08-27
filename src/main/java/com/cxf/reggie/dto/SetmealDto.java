package com.cxf.reggie.dto;

import com.cxf.reggie.entity.Setmeal;
import com.cxf.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {
    private String categoryName;

    private List<SetmealDish> setmealDishes;
}
