package com.job.feedloader.builders;

import com.job.feedloader.exceptions.FeedBuilderException;
import com.job.feedloader.models.Address;
import com.job.feedloader.models.CustomerOrder;
import com.job.feedloader.models.MasterData;
import com.job.feedloader.models.Customer;
import com.job.feedloader.utils.FeedInputStringUtil;

import java.text.ParseException;

public class OrderBuilder {

    public CustomerOrder buildUserOrder(MasterData masterData, Customer customer) throws FeedBuilderException {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setName(masterData.getCol10());
        customerOrder.setPrice(Double.parseDouble(masterData.getCol11()));
        try {
            customerOrder.setDeliveryDate(FeedInputStringUtil.parseStringDate(masterData.getCol12()));
        } catch (ParseException e) {
            throw new FeedBuilderException("invalid date coming from the feed data for order id : "
                    + masterData.getCol9());
        }

        AddressBuilder builder = new AddressBuilder();
        customerOrder.setCustomer(customer);
        Address deliveryAddress = builder.buildDeliveryAddress(masterData, customerOrder);
        customerOrder.setDeliveryAddress(deliveryAddress);
        return customerOrder;
    }
}
