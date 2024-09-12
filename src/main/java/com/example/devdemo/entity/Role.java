package com.example.devdemo.entity;


import lombok.Getter;

@Getter
public enum Role {

    normal(001,"普通学生"),
    squad_leader(002,"班长");

    Role(Integer code,String description){
        this.code = code;
        this.description = description;
    }

    public Integer code;

    public String description;

}
