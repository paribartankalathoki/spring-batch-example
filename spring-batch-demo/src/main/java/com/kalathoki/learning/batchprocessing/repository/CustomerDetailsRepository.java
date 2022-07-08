package com.kalathoki.learning.batchprocessing.repository;


import com.kalathoki.learning.batchprocessing.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 4:51 PM
 */

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails,Integer> {
}
