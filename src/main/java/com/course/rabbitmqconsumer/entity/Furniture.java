package com.course.rabbitmqconsumer.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Furniture {

    private String name;
    private String color;
    private String material;
    private BigDecimal price;
}
