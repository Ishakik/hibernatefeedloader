package com.job.feedloader.builders;

import com.job.feedloader.models.Address;
import com.job.feedloader.models.CustomerOrder;
import com.job.feedloader.models.MasterData;

public class AddressBuilder {

    public Address buildDeliveryAddress(MasterData masterData, CustomerOrder customerOrder) {
        Address address = new Address();
        address.setLine1(masterData.getCol13());
        address.setLine2(masterData.getCol14());
        address.setCity(masterData.getCol15());
        address.setState(masterData.getCol16());
        address.setZip(masterData.getCol17());
        address.setCustomerOrder(customerOrder);
        return address;
    }
}
