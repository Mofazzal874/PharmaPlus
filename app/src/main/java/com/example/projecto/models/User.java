package com.example.projecto.models;

public class User {
    String name;
    String email;
    String password;

    public User(){}

    public User(String a,String b, String c){
        this.name = a;
        this.email = b;
        this.password = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


