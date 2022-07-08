package com.kalathoki.learning.batchpartitioning.config;


import com.kalathoki.learning.batchpartitioning.entity.Customer;
import com.kalathoki.learning.batchpartitioning.repository.CustomerRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 6:40 PM
 */

@Component
public class CustomerWriter implements ItemWriter<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Customer> list) throws Exception {
        System.out.println("Thread Name : -"+Thread.currentThread().getName());
        customerRepository.saveAll(list);
    }
}
