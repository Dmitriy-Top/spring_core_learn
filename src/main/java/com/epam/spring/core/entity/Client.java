package com.epam.spring.core.entity;

/**
 * Created by Dmitrii_Topolnik on 6/28/2017.
 */
public class Client {
    private String id;
    private String fullname;
    private String greeting;

    public Client(String id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
