package com.course.rabbitmqconsumer.entity;

import lombok.Data;

@Data
public class Picture {

    private String name;
    private String type;
    private String source;
    private long size;
}
