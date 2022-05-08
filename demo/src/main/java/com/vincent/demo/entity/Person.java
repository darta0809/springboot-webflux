package com.vincent.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Person {
    @Id
    private Long id;
    private String name;
    private String address;
}
