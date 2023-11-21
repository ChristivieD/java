package com.christivie.demo.day21;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DOB {
    @JsonProperty("date")
    private Date date;
    @JsonProperty("age")
    private int age;

    public Date getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "DOB{" +
                "date=" + date +
                ", age=" + age +
                '}';
    }
}
