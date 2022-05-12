package com.vincent.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_mysql")
public class UserMysql {

    @Id
    private int id;
    private String username;
    private String address;
}
