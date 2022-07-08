package com.kalathoki.learning.batchpartitioning.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 5:13 PM
 */

@Entity
@Data
public class Customer {

    @Id
    @Column(name = "customer_id")
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private String contactNo;

    private String country;

    private String dob;
}
