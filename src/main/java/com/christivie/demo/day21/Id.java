package com.christivie.demo.day21;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Id {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Id{" +
                "name='" + name + '\'' +
                '}';
    }
}
