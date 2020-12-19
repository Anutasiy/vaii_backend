package com.mati.vaii.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String surname;
    public Users() {
    }


//    public Long getId() {
//        return id;
//    }

    public Users(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Users(String email, String role, String name, String surname) {
        this.email = email;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
