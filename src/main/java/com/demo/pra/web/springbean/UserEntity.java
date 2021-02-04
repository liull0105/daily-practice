package com.demo.pra.web.springbean;

import lombok.Data;

@Data
public class UserEntity {

    private Integer userId;

    private String userName;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId + "userId" +
                ", userName='" + userName + '\'' +
                '}';
    }
}
