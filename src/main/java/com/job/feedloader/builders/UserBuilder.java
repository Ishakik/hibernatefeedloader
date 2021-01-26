package com.job.feedloader.builders;

import com.job.feedloader.exceptions.FeedBuilderException;
import com.job.feedloader.models.Customer;
import com.job.feedloader.models.CustomerOrder;
import com.job.feedloader.models.MasterData;

public class UserBuilder {

    public Customer build(MasterData masterData) throws FeedBuilderException {
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(masterData.getCol1()));
        customer.setFirstName(masterData.getCol2());
        customer.setLastName(masterData.getCol3());

        OrderBuilder orderBuilder = new OrderBuilder();
        CustomerOrder customerOrder = orderBuilder.buildUserOrder(masterData, customer);
        customer.setCustomerOrder(customerOrder);
        return customer;
    }
}
