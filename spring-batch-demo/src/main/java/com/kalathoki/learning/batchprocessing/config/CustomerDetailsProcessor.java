package com.kalathoki.learning.batchprocessing.config;

import com.kalathoki.learning.batchprocessing.entity.CustomerDetails;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author:- Paribartan Kalathoki
 * @created on:- 08 Jul, 2022 at 5:20 PM
 */
public class CustomerDetailsProcessor implements ItemProcessor<CustomerDetails,CustomerDetails> {

    @Override
    public CustomerDetails process(CustomerDetails customerDetails) throws Exception {
        return customerDetails;
    }
}
