package com.job.feedloader.configuration;

import java.util.ArrayList;
import java.util.List;

public interface CSVConfiguration {
    int FEED_FILE_COLUMN_LENGTH = 17;
    List<String> FEED_HEADERS = new ArrayList<String>(){{
        add("id");
        add("firstname");
        add("lastname");
        add("line1");
        add("line2");
        add("city");
        add("state");
        add("zip");
        add("orderid");
        add("ordername");
        add("orderprice");
        add("deliverydate");
        add("line");
        add("line2");
        add("city");
        add("state");
        add("zip");
    }};

    String FEED_DATE_FORMAT = "dd/MM/yyyy";
}
