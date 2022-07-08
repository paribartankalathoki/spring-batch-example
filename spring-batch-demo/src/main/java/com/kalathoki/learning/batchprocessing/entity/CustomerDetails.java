package com.kalathoki.learning.batchprocessing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 4:50 PM
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

    @Id
    @Column(name = "customer_info_id")
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private String contactNo;

    private String country;

    private String dob;

}
