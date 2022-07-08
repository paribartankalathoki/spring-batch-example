package com.kalathoki.learning.batchpartitioning.repository;

import com.kalathoki.learning.batchpartitioning.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 5:16 PM
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
