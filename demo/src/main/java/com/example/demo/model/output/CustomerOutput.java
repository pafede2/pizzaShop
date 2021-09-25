package com.example.demo.model.output;

import java.util.List;
import java.util.UUID;

public class CustomerOutput {

    private UUID uuid;

    private String firstName;

    private String lastName;


    private CustomerOutput() {
    }

    public static final class CustomerOutputBuilder {

        private UUID uuid;

        private String firstName;

        private String lastName;

        public CustomerOutputBuilder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public CustomerOutputBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerOutputBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerOutput build() {
            CustomerOutput customerOutput = new CustomerOutput();
            customerOutput.setUuid(this.uuid);
            customerOutput.setFirstName(this.firstName);
            customerOutput.setLastName(this.lastName);
            return customerOutput;
        }
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
