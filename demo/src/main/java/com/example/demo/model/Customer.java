package com.example.demo.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @Column
    private int id;

    @Column
    @Type(type="uuid-char")
    private UUID uuid;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String cardNumber;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
