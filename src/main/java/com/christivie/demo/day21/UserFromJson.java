package com.christivie.demo.day21;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserFromJson {
    @JsonProperty("results")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
}

class User {
    @JsonProperty("name")
    private Name name;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("cell")
    private String cell;
    @JsonProperty("nat")
    private String nat;


    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                "\n\tgender='" + gender + '\'' +
                "\n\tlocation=" + location +
                "\n\temail='" + email + '\'' +
                "\n\tphone='" + phone + '\'' +
                "\n\tcell='" + cell + '\'' +
                "\n\tnat='" + nat + '\'' +
                "}\n";
    }
}

class Name {
    @JsonProperty("title")
    private String title;
    @JsonProperty("first")
    private String first;
    @JsonProperty("last")
    private String last;

    @Override
    public String toString() {
        return title + ' ' + first + ' ' + last ;
    }
}

class Location {
    @JsonProperty("street")
    private Street street;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("postcode")
    private String postcode;
    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("timezone")
    private Timezone timezone;


    @Override
    public String toString() {
        return "{" +
                "street=" + street +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                ", coordinates=" + coordinates +
                ", timezone=" + timezone +
                '}';
    }
}

class Street {
    @JsonProperty("number")
    private String number;
    @JsonProperty("name")
    private String name;


    @Override
    public String toString() {
        return "{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
class Coordinates {
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;

    @Override
    public String toString() {
        return "{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
class Timezone {
    @JsonProperty("offset")
    private String offset;
    @JsonProperty("description")
    private String description;

    @Override
    public String toString() {
        return "{" +
                "offset='" + offset + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
