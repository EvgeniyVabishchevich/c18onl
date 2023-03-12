package com.tms.webshop.model;

import java.time.LocalDate;

public class User {
    private UserType userType;
    private String login;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    private int id;

    public User(int id, String login, UserType userType, String name, String surname, String email, LocalDate birthday) {
        this.id = id;
        this.userType = userType;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }
}
