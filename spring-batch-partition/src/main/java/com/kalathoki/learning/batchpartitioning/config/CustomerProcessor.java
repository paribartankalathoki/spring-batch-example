package com.kalathoki.learning.batchpartitioning.config;

import com.kalathoki.learning.batchpartitioning.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 5:36 PM
 */
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        return customer;
    }
}
