package com.example.devDemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Accessors(chain = true)
public class Student implements Serializable {

    public String id;

    public String name;

    public Integer age;

    public Role role;

    public Optional<Role> optionalRole(){
        return Optional.ofNullable(role);
    }
}
