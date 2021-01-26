package com.job.feedloader.utils;

import com.job.feedloader.configuration.CSVConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedInputStringUtil {
    public static String nullifyIfEmpty(String s){
        if(s.isEmpty()) {
            return null;
        } else {
            return s;
        }
    }

    public static Date parseStringDate(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(CSVConfiguration.FEED_DATE_FORMAT);
        return sdf.parse(s);
    }
}
