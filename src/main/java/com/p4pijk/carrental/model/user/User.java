package com.p4pijk.carrental.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private boolean status;
    private UserRole userRole;
    private double balance;
}
