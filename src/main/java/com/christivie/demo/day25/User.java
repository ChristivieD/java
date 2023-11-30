package com.christivie.demo.day25;

import java.time.Instant;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private char[] password;
    private String language;
    private String status;
    private String privileges;
    private Instant created_at;
    private Instant last_logged_in;
    private Instant updated_at;

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String phone, char[] password, String language, String status, String privileges, Instant created_at, Instant last_logged_in, Instant updated_at) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.language = language;
        this.status = status;
        this.privileges = privileges;
        this.created_at = created_at;
        this.last_logged_in = last_logged_in;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password=" + Arrays.toString(password) +
                ", language='" + language + '\'' +
                ", status='" + status + '\'' +
                ", privileges='" + privileges + '\'' +
                ", created_at=" + created_at +
                ", last_logged_in=" + last_logged_in +
                ", updated_at=" + updated_at +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/email#basic_validation
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        if(!email.matches(regex)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        String passwordStr = String.valueOf(password);
        Pattern p = Pattern.compile("^" +
                "(?=.*[0-9])" + // a digit must occur at least once
                "(?=.*[a-z])" + // a lower case letter must occur at least once
                "(?=.*[A-Z])" + // an upper case letter must occur at least once
                 "(?=.*[@#$%^&+=])" + // a special character must occur at least once
                "(?=\\S+$)" + // no whitespace allowed in the entire string
                ".{8,}" + // anything, at least eight characters
                "$");
        Matcher m = p.matcher(passwordStr);
        if (!m.matches()) {
            throw new IllegalArgumentException("Password must contain at least 8 characters, with 1 digit, 1 lowercase,and 1 uppercase letter");
        }
        this.password = passwordStr.toCharArray();
    }

    public String getLanguage() {
        return language;
    }

    public String getStatus() {
        return status;
    }
    public String getPrivileges() {
        return privileges;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public Instant getLast_logged_in() {
        return last_logged_in;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }
}
