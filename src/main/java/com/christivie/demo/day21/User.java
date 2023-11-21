package com.christivie.demo.day21;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Comparable<User> {
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
    @JsonProperty("registered")
    private String registered;
    @JsonProperty("id")
    private String id;
    @JsonProperty("picture")
    private Picture picture;


    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getCell() {
        return cell;
    }

    public String getNat() {
        return nat;
    }
    public Picture getPicture() {
        return picture;
    }

    public String getRegistered() {
        return registered;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", cell='" + cell + '\'' +
                ", nat='" + nat + '\'' +
                ", registered='" + registered + '\'' +
                ", id='" + id + '\'' +
                ", picture=" + picture +
                '}';
    }

    @Override
    public int compareTo(User o) {
        int result = this.getName().getLast().compareTo(o.getName().getLast());
        if(result ==0){
            result = this.getName().getFirst().compareTo(o.getName().getFirst());
        }
        return result;
    }
}
