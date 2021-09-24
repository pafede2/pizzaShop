package com.example.demo.model.output;

import java.util.UUID;

public class CustomerOutput {

    private UUID uuid;

    private String firstName;

    private String lastName;

    public CustomerOutput() {
    }

    public CustomerOutput(UUID uuid, String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getUuid() {
        return uuid;
    }
}
