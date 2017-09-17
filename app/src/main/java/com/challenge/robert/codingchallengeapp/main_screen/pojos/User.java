package com.challenge.robert.codingchallengeapp.main_screen.pojos;

/**
 * Created by Robert on 17.09.2017.
 */

public class User {
    public static final String JSON_KEY_FIRST_NAME = "first_name";
    public static final String JSON_KEY_LAST_NAME = "last_name";

    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s, %s", firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
