package com.goodboy.edu.crud.elephants.service.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private long id;
    private String username;
    private String password;
}
